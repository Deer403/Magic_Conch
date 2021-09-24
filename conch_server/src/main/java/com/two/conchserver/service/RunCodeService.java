package com.two.conchserver.service;

import com.two.conchserver.utils.ProcessResult;
import com.two.conchserver.utils.Config;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RunCodeService {

    private final Config config;
    private static AtomicLong nextLong = new AtomicLong(System.currentTimeMillis());

    public RunCodeService(Config config) {
        this.config = config;
    }

    //运行python文件
    public ProcessResult runCode(String type, String code) throws IOException, InterruptedException {

        //1.首先需要将前端传进来的代码保存到本地
        //获取系统缓存文件位置
        String tmpDir = System.getProperty("java.io.tmpdir");
        // 随机文件夹的名字
        File pwd = Paths.get(tmpDir, String.format("%016x", nextLong.incrementAndGet())).toFile();
        // 新建文件夹
        pwd.mkdirs();

        //将传入的代码写入到文件中
        try (Writer writer = new BufferedWriter(new FileWriter(new File(pwd, "Main.py"), Charset.defaultCharset()))) {
            writer.write(code);
        }

        //存放运行结果
        ProcessBuilder pb = null;
        //运行程序文件
        pb = new ProcessBuilder().command(config.getPython(), "Main.py").directory(pwd);

        //合并进程的标准输出和错误输出
        pb.redirectErrorStream(true);
        Process p = pb.start();

        if (p.waitFor(5, TimeUnit.SECONDS)) {
            String result = null;
            try (InputStream input = p.getInputStream()) {
                result = readAsString(input, Charset.defaultCharset());
            }
            return new ProcessResult(p.exitValue(), result);
        } else {
            System.err.println(String.format("Error: process %s timeout. destroy forcibly.", p.pid()));
            p.destroyForcibly();
            return new ProcessResult(p.exitValue(), "运行超时");
        }
    }


    public String readAsString(InputStream input, Charset charset) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[102400];
        for (; ; ) {
            int n = input.read(buffer);
            if (n == (-1)) {
                break;
            }
            output.write(buffer, 0, n);
        }
        return output.toString(charset);
    }
}

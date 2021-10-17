package com.two.conchserver.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectVolumeResponse;
import com.github.dockerjava.api.command.ListVolumesResponse;
import com.github.dockerjava.api.model.Frame;
import com.two.conchserver.docker.DockerJavaClient;
import com.two.conchserver.utils.*;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RunCodeService {

    private final Config config;
    private static AtomicLong nextLong = new AtomicLong(System.currentTimeMillis());

    public RunCodeService(Config config) {
        this.config = config;
    }

    //本地运行python文件
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
        
        //设定超时时间
        if (p.waitFor(5, TimeUnit.SECONDS)) {
            String result = null;
            //获取输入流
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

    //连接docker运行各类编程语言程序
    public ProcessResult runCodeDocker(LanguageDetails type,String code) throws IOException {
        //1.首先需要将前端传进来的代码保存到本地
        //获取系统缓存文件位置
        String tmpDir = System.getProperty("java.io.tmpdir");
        // 随机文件夹的名字
        File pwd = Paths.get(tmpDir, String.format("%016x", nextLong.incrementAndGet())).toFile();
        // 新建文件夹
        pwd.mkdirs();

        //将传入的代码写入到文件中
        try (Writer writer = new BufferedWriter(new FileWriter(new File(pwd, type.getFileName()), Charset.defaultCharset()))) {
            writer.write(code);
        }
        //创建docker连接
        DockerJavaClient dockerJavaClient = new DockerJavaClient();
        DockerClient dockerClient = dockerJavaClient.getDockerClient();

        //创建并运行容器
        CreateContainerResponse container = dockerJavaClient.createContainer(dockerClient, type);
        dockerClient.startContainerCmd(container.getId()).exec();

        //在容器中创建文件目录并将程序复制进去
        String[] commands = new String[] {"bash", "-c",
                "mkdir "+DockerConfig.WORKING_DIR
        };
        dockerJavaClient.runCmd(dockerClient,container.getId(),commands);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dockerClient.copyArchiveToContainerCmd(container.getId())
                .withHostResource(pwd.getAbsolutePath()+"/"+type.getFileName())
                .withRemotePath(DockerConfig.WORKING_DIR)
                .exec();

        //进入程序所在文件夹目录并运行程序
        commands = new String[]{"bash","-c","cd "+DockerConfig.WORKING_DIR+" && "+type.getRunCommand(),
        };
        String frames = dockerJavaClient.runCmd(dockerClient, container.getId(), commands);

        //删除创建的容器
        dockerJavaClient.remoteContainer(dockerClient, container.getId());
        try{
            //运行完毕后需删除生成的文件
            System.out.println("[deleteFile]"+type.getFileName());
            FileUtils.deleteDirectory(pwd);//删除文件夹
        }catch (Exception ex){
            throw new BusinessException(ErrorEnums.DELETE_FAILURE_ERROR);
        }
        return  new ProcessResult(1,frames);
    }


    public String readAsString(InputStream input, Charset charset) throws IOException {
        //创建一个32字节（默认大小）的字节数组缓冲区
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[102400];
        for (; ; ) {
            int n = input.read(buffer);
            if (n == (-1)) {
                break;
            }
            //将输入流中的数据添加到字节数组缓冲区中
            output.write(buffer, 0, n);
        }
//         for (int n = input.read(buffer);n != (-1);n=input.read(buffer)){
//             //将输入流中的数据添加到字节数组缓冲区中
//             output.write(buffer, 0, n);
//         }
        //使用对应字符集解码缓冲区中的字符串
        return output.toString(charset);
    }

}

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

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RunCodeService {

    private static AtomicLong nextLong = new AtomicLong(System.currentTimeMillis());

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

}

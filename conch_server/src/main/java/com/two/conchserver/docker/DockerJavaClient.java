package com.two.conchserver.docker;

import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.two.conchserver.utils.DockerConfig;
import com.two.conchserver.utils.LanguageDetails;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*
    连接docker并执行一系列操作
 */
public class DockerJavaClient {

    //创建一个Docker连接
    public DockerClient getDockerClient(){

        //进行安全认证
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(DockerConfig.DOCKER_HOST)
                .withDockerTlsVerify(DockerConfig.DOCKER_TLS_VERIFY)
                .withDockerCertPath(DockerConfig.DOCKER_CERT_PATH)
                .withDockerConfig(DockerConfig.DOCKER_CONFIG)
                .build();

        //连接docker服务器
        DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

        //打印docker环境信息
        Info exec = dockerClient.infoCmd().exec();
        String s = JSONObject.toJSONString(exec);
        System.out.println("=========docker环境信息如下：============");
        System.out.println(s);
        System.out.println("======================================");
        return dockerClient;
    }

    //获取docker中所有镜像
    public List<Image> getImageList(DockerClient dockerClient){
        List<Image> imageList = dockerClient.listImagesCmd().exec();
        //输出所有镜像的名称
//        imageList.forEach(image -> System.out.println(image.getRepoTags()[0]));
        return imageList;
    }


    //创建docker容器
    public CreateContainerResponse createContainer(DockerClient dockerClient, LanguageDetails type){
        CreateContainerResponse container =  dockerClient.createContainerCmd(type.getImageName())
                .withName(type.getContainerName())    //给容器命名
                .withStdinOpen(true)
                .exec();
        return container;
    }

    //删除docker容器
    public void remoteContainer(DockerClient dockerClient,String containerId){
        //先停止此容器，再进行删除
        dockerClient.stopContainerCmd(containerId).exec();
        dockerClient.removeContainerCmd(containerId).exec();
    }

    //在容器中执行一条指令
    public String runCmd(DockerClient dockerClient, String containerId, String[] commands){
        //创建指令
        ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerId)
                .withAttachStdout(true)
                .withAttachStderr(true)
                .withCmd(commands).exec();
        final String[] objEnd = new String[1];
        try {
            dockerClient.execStartCmd(execCreateCmdResponse.getId()).exec(new ResultCallback.Adapter<>(){
                @Override
                public void onNext(Frame object) {
                    System.out.println("[Container指令运行中]");
                    objEnd[0] = object.toString();
                    System.out.println(object.toString());
                }

                @Override
                public void onComplete() {
                    System.out.println("[一条Container指令运行结束]");
                    super.onComplete();
                }
            }).awaitCompletion(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return objEnd[0];
    }

}

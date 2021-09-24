package com.two.conchserver.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.two.conchserver.utils.DockerConfig;

/*
    连接docker并执行一系列操作
 */
public class DockerJavaClient {

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

        return dockerClient;
    }

}

package com.two.magicconch.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:/config/docker-java.properties")
@Component
public class DockerConfig {

    public static String DOCKER_HOST;   //docker宿主机外网地址以及端口
    public static String DOCKER_TLS_VERIFY;     //启用/禁用 TLS 验证（在http和https协议之间切换）
    public static String DOCKER_CERT_PATH;    // TLS 验证所需证书的路径
    public static String DOCKER_CONFIG;   //其他docker配置文件路径，这里我没有，所以填写了证书的路径
    public static String API_VERSION;    //docker的api版本

    /*
       无法直接给静态变量注入值，这里使用@Value读取配置文件进行赋值
     */
    @Value("${DOCKER_HOST}")
    public void setDockerHost(String host) {
        DockerConfig.DOCKER_HOST = host;
    }

    @Value("${DOCKER_CERT_PATH}")
    public void setDockerCertPath(String dockerCertPath) {
        DOCKER_CERT_PATH = dockerCertPath;
    }

    @Value("${DOCKER_CONFIG}")
    public void setDockerConfig(String dockerConfig) {
        DOCKER_CONFIG = dockerConfig;
    }

    @Value("${DOCKER_TLS_VERIFY}")
    public void setDockerTlsVerify(String dockerTlsVerify) {
        DOCKER_TLS_VERIFY = dockerTlsVerify;
    }

    @Value("${API_VERSION}")
    public void setApiVersion(String apiVersion) {
        DockerConfig.API_VERSION = apiVersion;
    }
}

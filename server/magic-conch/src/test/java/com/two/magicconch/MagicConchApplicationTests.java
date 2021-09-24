package com.two.magicconch;

import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.model.Info;
import com.two.magicconch.docker.DockerJavaClient;
import com.two.magicconch.service.RunCodeService;
import com.two.magicconch.utils.DockerConfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class MagicConchApplicationTests {


	@Autowired
	private RunCodeService runCodeService;

	@Autowired
	private DockerConfig dockerConfig;

	@Test
	void contextLoads() throws IOException, InterruptedException {
		String code = "print(\"hello world\")";
		System.out.println(runCodeService.runCode("python",code).getOutput());
	}

	@Test
	void testDockerClient(){
		//创建docker连接
		DockerJavaClient dockerJavaClient = new DockerJavaClient();
        Info info = dockerJavaClient.getDockerClient().infoCmd().exec();
		String s = JSONObject.toJSONString(info);
		System.out.println("info:"+s);
		String dockerRootDir = info.getDockerRootDir();
		System.out.println(dockerRootDir);
	}
}

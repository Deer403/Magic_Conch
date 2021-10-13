package com.two.conchserver;

import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.two.conchserver.docker.DockerJavaClient;
import com.two.conchserver.service.RunCodeService;
import com.two.conchserver.utils.DockerConfig;

import com.two.conchserver.utils.LanguageDetails;
import com.two.conchserver.utils.ProcessResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ConchServerApplicationTests {


	@Autowired
	private RunCodeService runCodeService;

	@Test
	void contextLoads() throws IOException, InterruptedException {
		String code = "print(\"hello world\")";
		System.out.println(runCodeService.runCode("python",code).getOutput());
	}

	@Test
	void testDockerClient(){
		//创建docker连接
		DockerJavaClient dockerJavaClient = new DockerJavaClient();
		DockerClient dockerClient = dockerJavaClient.getDockerClient();
		Info info = dockerClient.infoCmd().exec();
		String s = JSONObject.toJSONString(info);
		System.out.println("info:"+s);
		String dockerRootDir = info.getDockerRootDir();
		System.out.println(dockerRootDir);

		dockerJavaClient.getImageList(dockerClient);
	}

	//测试docker创建容器运行代码
	@Test
	void testCreatDocker() throws IOException {
		String code = "print(\"hello world\")";
		LanguageDetails type = LanguageDetails.valueOf("PYTHON3");
		ProcessResult processResult = runCodeService.runCodeDocker(type, code);
		System.out.println(processResult);
	}
}

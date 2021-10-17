package com.two.conchserver;

import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ListContainersCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Info;
import com.two.conchserver.docker.DockerJavaClient;
import com.two.conchserver.model.CodeModel;
import com.two.conchserver.service.RunCodeService;
import com.two.conchserver.utils.DockerConfig;

import com.two.conchserver.utils.LanguageDetails;
import com.two.conchserver.utils.ProcessResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

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
	void testCreactDocker() throws IOException {
		String pyCode = "print(\"hello world\")";
		String goCode = "package main\n" +
				"\n" +
				"func main() {\n" +
				"    println(\"it's over 9000!\")\n" +
				"}";
		String cppCode = "#include <iostream>\n" +
				"using namespace std;\n" +
				" \n" +
				"// main() 是程序开始执行的地方\n" +
				" \n" +
				"int main()\n" +
				"{\n" +
				"   cout << \"Hello World\"; // 输出 Hello World\n" +
				"   return 0;\n" +
				"}";
		CodeModel codeModel = new CodeModel();
		codeModel.setCode(cppCode);
		codeModel.setType("CPP");
		LanguageDetails type = LanguageDetails.valueOf("CPP");
		ProcessResult processResult = runCodeService.runCodeDocker(LanguageDetails.valueOf(codeModel.getType()),codeModel.getCode());
		System.out.println(processResult);
	}

	//获取所有容器，并一一删除
	@Test
	void testGetContainerAndDelete(){
//创建docker连接
		DockerJavaClient dockerJavaClient = new DockerJavaClient();
		DockerClient dockerClient = dockerJavaClient.getDockerClient();
		List<Container> containerList = dockerClient.listContainersCmd().exec();
		System.out.println("=============输出所有容器=================");
		containerList.forEach(container -> {
			System.out.println("========正在删除["+container.getId()+"]==========");
			dockerClient.stopContainerCmd(container.getId()).exec();
			dockerClient.removeContainerCmd(container.getId()).exec();
		});
		System.out.println("=============完毕=================");


	}
}

package com.two.magicconch;

import com.two.magicconch.service.RunCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class MagicConchApplicationTests {


	@Autowired
	private RunCodeService runCodeService;

	@Test
	void contextLoads() throws IOException, InterruptedException {
		String code = "print(\"hello world\")";
		System.out.println(runCodeService.runCode("python",code).getOutput());

	}

}

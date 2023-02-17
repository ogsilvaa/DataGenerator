package com.silva.dataGenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.silva.dataGenerator.controller.StringController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DataGeneratorApplicationTests {

@Autowired
StringController controller;

	@Test
	void contextLoads() {
	 assertThat(controller).isNotNull();
	}

}

package com.silva.datagenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.silva.datagenerator.controller.StringController;

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

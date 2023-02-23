package com.silva.datagenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DataGeneratorApplicationTests {

  @Autowired
  ApplicationContext context;

  @Test
  void contextLoads() {
    assertThat(context).isNotNull();
  }

  @Test
  void main() {
    DataGeneratorApplication.main(new String[]{});
    assertThat(context).isNotNull();
  }

}

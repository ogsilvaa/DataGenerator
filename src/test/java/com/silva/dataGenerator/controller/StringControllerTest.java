package com.silva.dataGenerator.controller;

import com.silva.dataGenerator.DataGeneratorApplication;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DataGeneratorApplication.class)
@AutoConfigureMockMvc
public class StringControllerTest {
  @Autowired
  MockMvc mvc;
  @Test
  void generateString() throws Exception {
    var uri="/string";
    MvcResult mvcResult= mvc.perform(
        MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)
    )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  void generateList() throws Exception {
    var uri="/string/lista";
    var values= new ArrayList<String>();
    IntStream.range(0,10).forEach(i-> values.add(RandomStringUtils.random(10,true,false)));
    MvcResult mvcResult= mvc.perform(
            MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("values",
                    values.toArray(String[]::new)
                )
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(result -> values.contains(result))
        .andReturn();
  }
}

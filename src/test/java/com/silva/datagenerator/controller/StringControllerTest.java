package com.silva.datagenerator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silva.datagenerator.DataGeneratorApplication;
import com.silva.datagenerator.util.AbstractTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.hasLength;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DataGeneratorApplication.class)
class StringControllerTest extends AbstractTest {
  private final String LIST_URI = "/string/list";
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void generateString() throws Exception {
    String BASIC_URI = "/string";
    mvc.perform(
            MockMvcRequestBuilders.get(BASIC_URI)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isString())
        .andReturn();
  }

  @Test
  void generateStringNoZero() throws Exception {
    var BASIC_URI = "/string";
    var LONG_STRING = 8;
    mvc.perform(
            get(BASIC_URI)
                .queryParam("i", LONG_STRING + "")
                .accept(MediaType.APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isString())
        .andExpect(jsonPath("$").value(hasLength(LONG_STRING)))
        .andReturn();
  }

  @Test
  void generateList() throws Exception {
    var values = new ArrayList<String>();
    IntStream.range(0, 10).forEach(i -> values.add(RandomStringUtils.random(10, true, false)));
    mvc.perform(
            MockMvcRequestBuilders.get(LIST_URI)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("values",
                    values.toArray(String[]::new)
                )
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isString())
        .andReturn();
  }

  @Test
  void generateListPost() throws Exception {
    var values = new ArrayList<String>();
    IntStream.range(0, 10).forEach(i -> values.add(RandomStringUtils.random(10, true, false)));

    var requestJson = objectMapper.writeValueAsString(values);

    mvc.perform(
            post(LIST_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isString())
        .andReturn();
  }
}

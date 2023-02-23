package com.silva.datagenerator.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silva.datagenerator.domain.dto.StructRequest;
import com.silva.datagenerator.core.generator.implementation.GeneratorSubStruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataGeneratorGo {
  final StructRequest config;
  public String generate() throws JsonProcessingException {
    var result = GeneratorSubStruct.go(this.config);
    return new ObjectMapper().writeValueAsString(result);
  }
}

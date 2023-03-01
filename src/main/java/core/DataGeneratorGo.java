package core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silva.datagenerator.domain.dto.StructRequest;
import core.generator.implementation.GeneratorSubStruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataGeneratorGo {
  final StructRequest config;

  public String generate() throws JsonProcessingException {
    var result = GeneratorSubStruct.go(this.config);
    return new ObjectMapper().writeValueAsString(result);
  }
}

package com.silva.datagenerator.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.silva.datagenerator.domain.dto.StructRequest;
import java.util.List;

public interface GeneratorService {

  String generator(StructRequest struct) throws JsonProcessingException;

  String generateString(int i);

  String generateString(List<String> lista);
}
package com.silva.datagenerator.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.silva.datagenerator.domain.dto.StructRequest;

import java.util.List;

public interface GeneratorService {
  String generateString(Integer i);

  String generator(StructRequest struct) throws JsonProcessingException;

  String generateString(List<String> lista);
}
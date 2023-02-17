package com.silva.dataGenerator.domain.service;

public interface GeneratorService {
  String generateString(Integer i);
  String generateString(String[] values);

  String generator(String estructura);
}
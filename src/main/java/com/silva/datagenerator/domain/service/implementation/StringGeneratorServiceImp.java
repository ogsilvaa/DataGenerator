package com.silva.datagenerator.domain.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.silva.datagenerator.domain.dto.StructRequest;
import com.silva.datagenerator.domain.service.GeneratorService;
import core.DataGeneratorGo;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;



@Service
class StringGeneratorServiceImp implements GeneratorService {
  Random rdn = new Random();

  @Override
  public String generateString(int i) {
    if (i <= 0) {
      i = rdn.nextInt(10);
    }
    return RandomStringUtils.random(i, true, false);
  }

  @Override
  public String generateString(List<String> lista) {
    int i = rdn.nextInt(lista.size());
    return lista.get(i);
  }

  @Override
  public String generator(StructRequest struct) throws JsonProcessingException {
    var dataGenerator = new DataGeneratorGo(struct);
    return dataGenerator.generate();
  }
}
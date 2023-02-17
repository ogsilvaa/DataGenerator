package com.silva.dataGenerator.domain.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silva.dataGenerator.domain.service.GeneratorService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
class StringGeneratorServiceImp implements GeneratorService {
  Random rdn = new Random();
  @Override
  public String generateString(Integer i) {
    while(i==null || i<=0 ){
      i= rdn.nextInt(10);
    }
    return RandomStringUtils.random(i,true,false);
  }

  @Override
  public String generateString(String[] values) {
    int i = rdn.nextInt(values.length);
    return values[i];
  }

  @Override
  public String generator(String estructura) {
    //var retorno = new ObjectMapper().readValues(estructura, Object.class);
    return null;
  }
}
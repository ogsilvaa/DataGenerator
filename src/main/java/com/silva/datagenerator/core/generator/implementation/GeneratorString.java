package com.silva.datagenerator.core.generator.implementation;

import com.silva.datagenerator.domain.dto.PropertyRequest;
import com.silva.datagenerator.core.generator.GeneratorData;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class GeneratorString implements GeneratorData {
  Random rdn= new Random();
  @Override
  public Object generate(PropertyRequest property) {
    return RandomStringUtils.random(rdn.nextInt(10), true, false);
  }
}

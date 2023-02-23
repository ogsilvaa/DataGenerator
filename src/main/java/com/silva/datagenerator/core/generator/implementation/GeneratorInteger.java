package com.silva.datagenerator.core.generator.implementation;

import com.silva.datagenerator.domain.dto.PropertyRequest;
import com.silva.datagenerator.core.generator.GeneratorData;

import java.util.Random;

public class GeneratorInteger implements GeneratorData {
  Random rdn= new Random();
  @Override
  public Object generate(PropertyRequest property) {
    if (property.getMaxValue() != null) {
      if (property.getMinValue() != null) {
        int result;

        do {
          result = rdn.nextInt(property.getMaxValue());
        } while (result < property.getMinValue());

        return result;
      }
      return rdn.nextInt(property.getMaxValue());
    }
    return rdn.nextInt();
  }
}

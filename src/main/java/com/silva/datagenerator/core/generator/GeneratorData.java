package com.silva.datagenerator.core.generator;

import com.silva.datagenerator.domain.dto.PropertyRequest;

public interface GeneratorData {
  Object generate(PropertyRequest property);
}

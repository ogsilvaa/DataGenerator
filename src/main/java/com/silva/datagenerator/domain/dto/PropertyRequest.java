package com.silva.datagenerator.domain.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.silva.datagenerator.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequest {
  public String getName() {
    return StringUtil.snakeCase(name);
  }

  private String name;
  @JsonSetter(nulls = Nulls.SKIP)
  private TypeValue typeValue = TypeValue.STRING;
  private String nameList;
  private StructRequest subStructure;
  @JsonSetter(nulls = Nulls.SKIP)
  private Integer minValue;
  @JsonSetter(nulls = Nulls.SKIP)
  private Integer maxValue;
}

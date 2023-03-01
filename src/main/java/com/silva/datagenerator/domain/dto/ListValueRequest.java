package com.silva.datagenerator.domain.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class ListValueRequest {
  private String name;
  private List<Object> values;
}

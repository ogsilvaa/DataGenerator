package com.silva.datagenerator.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StructRequest {
  private List<PropertyRequest> properties;
  private List<ListValueRequest> listValues;
  private Integer quantity = 1;
}

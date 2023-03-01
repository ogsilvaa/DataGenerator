package com.silva.datagenerator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silva.datagenerator.DataGeneratorApplication;
import com.silva.datagenerator.domain.dto.ListValueRequest;
import com.silva.datagenerator.domain.dto.PropertyRequest;
import com.silva.datagenerator.domain.dto.StructRequest;
import com.silva.datagenerator.domain.dto.TypeValue;
import com.silva.datagenerator.util.AbstractTest;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DataGeneratorApplication.class)
public class GeneratorControllerTest extends AbstractTest {
  static final String URI = "/generator/generate";
  final ObjectMapper objectMapper = new ObjectMapper();
  final ListValueRequest<String> NOMBRES = ListValueRequest
      .<String>builder()
      .name("nombres")
      .values(List.of(
          "Analí",
          "Batolomé",
          "Cirilo",
          "Damian"
      ))
      .build();

  @Nested
  class OneProperty {
    @Test
    void oneProperty() throws Exception {
      var request = StructRequest
          .builder()
          .properties(List.of(PropertyRequest
              .builder()
              .name("ProPerty1")
              .build()))
          .build();
      var requestJson = objectMapper.writeValueAsString(request);

      var pathProperty = String.format("$[0].%s", request.getProperties().get(0).getName());

      mvc.perform(
              post(URI)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(requestJson)
          )
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath(pathProperty).exists())
          .andExpect(jsonPath(pathProperty).isString());
    }

    @Test
    void OnePropertyString() throws Exception {
      var request = StructRequest
          .builder()
          .properties(List.of(PropertyRequest
              .builder()
              .name("Property1")
              .typeValue(TypeValue.STRING)
              .build()))
          .build();
      var requestJson = objectMapper.writeValueAsString(request);

      var pathProperty = String.format("$[0].%s", request.getProperties().get(0).getName());

      mvc.perform(
              post(URI)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(requestJson)
          )
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath(pathProperty).exists())
          .andExpect(jsonPath(pathProperty).isString());
    }

    @Test
    void OnePropertyInteger() throws Exception {
      var request = StructRequest
          .builder()
          .properties(List.of(PropertyRequest
              .builder()
              .name("Propiedad1")
              .typeValue(TypeValue.INTEGER)
              .build()))
          .build();
      var requestJson = objectMapper.writeValueAsString(request);

      var pathProperty = String.format("$[0].%s", request.getProperties().get(0).getName());

      mvc.perform(
              post(URI)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(requestJson)
          )
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath(pathProperty).exists())
          .andExpect(jsonPath(pathProperty).isNumber());
    }

    @Test
    void OnePropertyFromList() throws Exception {
      var request = StructRequest
          .builder()
          .properties(List.of(PropertyRequest
              .builder()
              .name("Property1")
              .typeValue(TypeValue.STRING)
              .nameList("nombres")
              .build()))
          .listValues(List.of(NOMBRES))
          .build();
      var requestJson = objectMapper.writeValueAsString(request);

      var pathProperty = String.format("$[0].%s", request.getProperties().get(0).getName());

      mvc.perform(
              post(URI)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(requestJson)
          )
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath(pathProperty).exists())
          .andExpect(jsonPath(pathProperty).isString());
    }

    @Test
    void OneComplexProperty() throws Exception {
      var subStruct = StructRequest
          .builder()
          .properties(List.of(PropertyRequest
              .builder()
              .name("SubProperty")
              .build()))
          .build();

      var request = StructRequest
          .builder()
          .properties(List.of(PropertyRequest
              .builder()
              .name("ComplexProperty")
              .subStructure(subStruct)
              .typeValue(TypeValue.SUB_STRUCT)
              .build()))
          .build();
      var requestJson = objectMapper.writeValueAsString(request);

      var pathComplexProperty = String.format("$[0].%s", request.getProperties().get(0).getName());
      var pathSubProperty = String.format("%s[0].%s",
          pathComplexProperty,
          subStruct.getProperties().get(0).getName());

      mvc.perform(
              post(URI)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(requestJson)
          )
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath(pathComplexProperty).exists())
          .andExpect(jsonPath(pathComplexProperty, hasSize(1)))
          .andExpect(jsonPath(pathSubProperty).exists())
          .andExpect(jsonPath(pathSubProperty).isString());
    }
  }

  @Nested
  class ManyProperties {
    @Test
    void manyProperties() throws Exception {
      var propertyWithoutType = PropertyRequest
          .builder()
          .name("Propiedad1")
          .build();
      var propertySpace = PropertyRequest
          .builder()
          .name("Propiedad 2")
          .build();
      var propertyTypeString = PropertyRequest
          .builder()
          .name("Propiedad de cadena")
          .typeValue(TypeValue.STRING)
          .build();
      var propertyTypeInteger = PropertyRequest
          .builder()
          .name("Propiedad entero")
          .typeValue(TypeValue.INTEGER)
          .build();
      var propertyFromList = PropertyRequest
          .builder()
          .name("Propiedad de una lista")
          .nameList(NOMBRES.getName())
          .build();

      var request = StructRequest
          .builder()
          .properties(List.of(propertyWithoutType,
              propertySpace,
              propertyTypeString,
              propertyTypeInteger,
              propertyFromList))
          .listValues(List.of(NOMBRES))
          .build();
      var requestJson = objectMapper.writeValueAsString(request);

      mvc.perform(
              post(URI)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(requestJson)
          )
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$[0].propiedad1").exists())
          .andExpect(jsonPath("$[0].propiedad_2").exists())
          .andExpect(jsonPath("$[0].propiedad_de_cadena").exists())
          .andExpect(jsonPath("$[0].propiedad_entero").exists())
          .andExpect(jsonPath("$[0].propiedad_de_una_lista").exists())
          .andExpect(jsonPath("$[0].propiedad1").isString())
          .andExpect(jsonPath("$[0].propiedad_2").isString())
          .andExpect(jsonPath("$[0].propiedad_de_cadena").isString())
          .andExpect(jsonPath("$[0].propiedad_entero").isNumber())
          .andExpect(jsonPath("$[0].propiedad_de_una_lista").isString())
      ;
    }
  }
}

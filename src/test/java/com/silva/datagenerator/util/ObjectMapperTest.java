package com.silva.datagenerator.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ObjectMapperTest {
  @Data
  @JsonInclude(Include.NON_NULL)
  public static class Prueba {
    private String propiedad;
    private PruebaInterna interno;
    private List<String> lista;
    private List<PruebaInterna> listaCompleja;
  }

  @Data
  public static class PruebaInterna {
    private String propiedadInterna;
  }

  ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void SerializaConUnaPropiedad() throws JsonProcessingException {
    var prueba = new Prueba();
    prueba.setPropiedad("Valor de Prueba");
    var result = objectMapper.writeValueAsString(prueba);
    assertEquals("{\"propiedad\":\"Valor de Prueba\"}", result);
  }

  @Test
  void SerializeConPropiedadCompleja() throws JsonProcessingException {
    var prueba = new Prueba();
    var interno = new PruebaInterna();
    interno.setPropiedadInterna("Valor Interno");
    prueba.setPropiedad("Valor de Prueba");
    prueba.setInterno(interno);

    var result = objectMapper.writeValueAsString(prueba);

    assertEquals("{\"propiedad\":\"Valor de Prueba\",\"interno\":{\"propiedadInterna\":\"Valor Interno\"}}", result);
  }

  @Test
  void SerializeConPropiedadLista() throws JsonProcessingException {
    var prueba = new Prueba();
    var interno = new PruebaInterna();

    interno.setPropiedadInterna("Valor Interno");

    prueba.setPropiedad("Valor de Prueba");
    prueba.setInterno(interno);
    prueba.setLista(List.of("Alfa", "Beta", "Gamma"));

    var result = objectMapper.writeValueAsString(prueba);

    assertEquals("{\"propiedad\":\"Valor de Prueba\",\"interno\":{\"propiedadInterna\":\"Valor Interno\"}}", result);
  }

  @Test
  void SerializeConPropiedadListaCompleja() throws JsonProcessingException {
    var prueba = new Prueba();
    var interno = new PruebaInterna();

    var internoA = new PruebaInterna();
    var internoB = new PruebaInterna();
    var internoC = new PruebaInterna();

    interno.setPropiedadInterna("Valor Interno");
    internoA.setPropiedadInterna("Valor A");
    internoB.setPropiedadInterna("Valor B");
    internoC.setPropiedadInterna("Valor C");

    prueba.setPropiedad("Valor de Prueba");
    prueba.setInterno(interno);
    prueba.setLista(List.of("Alfa", "Beta", "Gamma"));
    prueba.setListaCompleja(List.of(internoA, internoB, internoC));

    var result = objectMapper.writeValueAsString(prueba);

    assertEquals("{\"propiedad\":\"Valor de Prueba\",\"interno\":{\"propiedadInterna\":\"Valor Interno\"}}", result);
  }

  @Test
  void SerializeConDatos() throws JsonProcessingException {
    var prueba = new HashMap<String, Object>();
    prueba.put("algo", "algodon");
    var result = objectMapper.writeValueAsString(prueba);
    assertEquals("{\"algo\":\"algodon\"}", result);
  }
}

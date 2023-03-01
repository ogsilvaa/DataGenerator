package com.silva.datagenerator.controller;

import com.silva.datagenerator.domain.service.GeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/string")
@RequiredArgsConstructor
public class StringController {
  private final GeneratorService generatorService;

  @GetMapping()
  @Operation(
      summary = "Genera una cadena",
      description = "Prueba de generación de String",
      parameters = {
          @Parameter(
              name = "i",
              example = "10",
              description = "longitud de la cadena a crear"
          )
      },
      responses = {
          @ApiResponse(description = "Cadena Generada correctamente",
              responseCode = "200",
              content = @Content(
                  mediaType = "text/plain",
                  schema = @Schema(
                      implementation = String.class,
                      maxLength = 100),
              examples = @ExampleObject(
                  name = "Ejemplo",
                  description = "AbCdEfG",
                  summary = "Ejemplo",
                  value = "ABCDE"
              )
              )
          )
      })
  String generateString(
      @RequestParam(value = "i", required = false, defaultValue = "0") Integer i
  ) {
    return generatorService.generateString(i);
  }

  @GetMapping("/list")
  String generateString(
      @RequestParam(value = "values") String[] values
  ) {
    return generatorService.generateString(Arrays.stream(values).collect(Collectors.toList()));
  }

  @PostMapping("/list")
  String generateString(@RequestBody List<String> list) {
    return generatorService.generateString(list);
  }
}

package com.silva.datagenerator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.silva.datagenerator.domain.dto.StructRequest;
import com.silva.datagenerator.domain.service.GeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generator")
@RequiredArgsConstructor
public class GeneratorController {
  private final GeneratorService generatorService;

  @GetMapping()
  String funciona() {
    return "funciona!!";
  }

  @PostMapping(
      value = "/generate",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  String generator(@RequestBody StructRequest struct) throws JsonProcessingException {
    return generatorService.generator(struct);
  }
}

package com.silva.datagenerator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.silva.datagenerator.domain.dto.StructRequest;
import com.silva.datagenerator.domain.service.GeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generator")
@RequiredArgsConstructor
public class GeneratorController {
  private final GeneratorService generatorService;
  @GetMapping()
  String works() {
    return "works!!";
  }

  @PostMapping(
      value = "/generate",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  String generator(@RequestBody StructRequest struct) throws JsonProcessingException {
    return generatorService.generator(struct);
  }
}

package com.silva.dataGenerator.controller;

import com.silva.dataGenerator.domain.service.GeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/generator")
@RequiredArgsConstructor
public class GeneratorController {
  private final GeneratorService generatorService;
  @PostMapping()
  String generator(String estructura){
    return generatorService.generator(estructura);
  }
}

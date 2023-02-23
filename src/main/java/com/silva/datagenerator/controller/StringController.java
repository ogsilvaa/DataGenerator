package com.silva.datagenerator.controller;

import com.silva.datagenerator.domain.service.GeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/string")
@RequiredArgsConstructor
public class StringController {
  private final GeneratorService generatorService;
  @GetMapping()
  String generateString(
      @RequestParam(value = "i", required = false) Integer i
  ) {
    return generatorService.generateString(i);
  }

  @GetMapping("/lista")
  String generateString(
      @RequestParam(value = "values") String[] values
  ) {
    return generatorService.generateString(Arrays.stream(values).collect(Collectors.toList()));
  }
  @PostMapping("/lista")
  String generateString(@RequestBody List<String> lista){
    return generatorService.generateString(lista);
  }
}

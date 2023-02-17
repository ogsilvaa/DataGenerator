package com.silva.dataGenerator.controller;

import java.util.Random;

import com.silva.dataGenerator.domain.service.GeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/string")
@RequiredArgsConstructor
public class StringController {
    private final GeneratorService generatorService;
    Random rdn = new Random();
    @GetMapping()
    String generateString(
        @RequestParam(value="i", required = false) Integer i
    ){
        return generatorService.generateString(i);
    }
    @GetMapping("/lista")
    String generateString(
        @RequestParam(value="values") String[] values
    ){
        return generatorService.generateString(values);
    }
}

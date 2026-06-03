package com.example.helloworld.controller;

import com.example.helloworld.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/add")
    public double add(@RequestParam int a, @RequestParam int b)
    {
        return calculatorService.add(a,b);
    }

    @GetMapping("/subtract")
    public double subtract(@RequestParam int a, @RequestParam int b)
    {
        return calculatorService.subtract(a,b);
    }

    @GetMapping("/divide")
    public double divide(@RequestParam int a, @RequestParam int b)
    {
        return calculatorService.divide(a,b);
    }

    @GetMapping("/multiply")
    public double multiply(@RequestParam int a, @RequestParam int b)
    {
        return calculatorService.multiply(a,b);
    }

}

package com.example.helloworld.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public double add(int a, int b)
    {
        return  a + b;
    }

    public double subtract(int a, int b)
    {
        return a - b;
    }

    public double divide(int a, int b) {
        return a / b;
    }

    public double multiply(int a, int b) {
        return a * b;
    }
}

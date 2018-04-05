package com.intraway.fizzbuzz.controller;

import com.intraway.fizzbuzz.repository.OperationsRepository;
import com.intraway.fizzbuzz.response.NumbersCharacteristics;
import com.intraway.fizzbuzz.service.FizzBuzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FizzBuzzController {

    private FizzBuzzService fizzBuzzService;
    private OperationsRepository operationsRepository;

    @Autowired
    public FizzBuzzController(FizzBuzzService fizzBuzzService, OperationsRepository operationsRepository) {
        this.fizzBuzzService =fizzBuzzService;
        this.operationsRepository = operationsRepository;
    }

    public FizzBuzzController() {
    }

    @GetMapping("/fizzbuzz/{min}/{max}")
    @ResponseStatus(HttpStatus.OK)
    public NumbersCharacteristics getNumbersList(@PathVariable int min, @PathVariable int max) {

         return fizzBuzzService.getNumbersList(min, max);
    }

}

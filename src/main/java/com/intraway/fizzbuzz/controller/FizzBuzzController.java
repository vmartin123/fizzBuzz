package com.intraway.fizzbuzz.controller;

import com.intraway.fizzbuzz.exceptions.BadRequest;
import com.intraway.fizzbuzz.model.Operation;
import com.intraway.fizzbuzz.repository.OperationsRepository;
import com.intraway.fizzbuzz.response.NumbersCharacteristics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class FizzBuzzController {

    private OperationsRepository operationsRepository;

    @Autowired
    public FizzBuzzController(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    public FizzBuzzController() {
    }

    private boolean buzz;
    private boolean fizz;
    private final String NO_MULTIPLOS = "No se encontraron múltiplos";
    private final String MULTIPLOS_DE_3 = "Se encontraron múltiplos de 3";
    private final String MULTIPLOS_DE_5 = "Se encontraron múltiplos de 5";
    private final String MULTIPLOS_DE_3_Y_5 = "Se encontraron múltiplos de 3 y de 5";

    @GetMapping("/fizzbuzz/{min}/{max}")
    @ResponseStatus(HttpStatus.OK)
    public NumbersCharacteristics getNumbersList(@PathVariable int min, @PathVariable int max) {
        fizz = false;
        buzz = false;

        validateRange(min, max);
        String items = validateNumbersMultiples(min, max);

        String description = NO_MULTIPLOS;
        if (fizz && buzz) {
            description = MULTIPLOS_DE_3_Y_5;
        } else if (fizz) {
            description = MULTIPLOS_DE_3;
        } else if (buzz) {
            description = MULTIPLOS_DE_5;
        }

        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyhhmmss"));

        int id = saveOperation(min, max, currentDate);

        NumbersCharacteristics nc = new NumbersCharacteristics();
        nc.setTimestamp(currentDate);
        nc.setCode(String.valueOf(id));
        nc.setDescription(description);
        nc.setList(items);

        return nc;
    }

    public void validateRange(int min, int max) {
        if (min > max) {
            throw new BadRequest("Los parámetros enviados son incorrectos");
        }
    }

    public String validateNumbersMultiples(int min, int max) {
        StringBuilder builder = new StringBuilder();

        for (int i = min; i <= max; i++) {
            if (i % 15 == 0) {
                builder.append("FizzBuzz");
                builder.append(",");
            } else if (i % 3 == 0) {
                builder.append("Fizz");
                builder.append(",");
                fizz = true;
            } else if (i % 5 == 0) {
                builder.append("Buzz");
                builder.append(",");
                buzz = true;
            } else {
                builder.append(Integer.toString(i));
                builder.append(",");
            }
        }
        builder.setLength(builder.length()-1);

        return builder.toString();
    }

    private int saveOperation(int min, int max, String currentDate) {
        Operation operation = new Operation(min, max, currentDate);

        // persist the operation using spring data
        return operationsRepository.save(operation).getId().intValue();
    }
}

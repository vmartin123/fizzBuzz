package com.intraway.fizzbuzz.config;

import java.io.IOException;
import java.util.logging.Logger;

import com.intraway.fizzbuzz.exceptions.BadRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    private final Logger log = Logger.getLogger(RestResponseEntityExceptionHandler.class.getName());

    @ExceptionHandler(Exception.class)
    void handleExceptions(Exception ex, HttpServletResponse response) throws IOException {
        log.severe(ex.getMessage());

        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(BadRequest.class)
    void handleBadRequests(BadRequest ex, HttpServletResponse response) throws IOException {
        log.severe(ex.getMessage());

        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}

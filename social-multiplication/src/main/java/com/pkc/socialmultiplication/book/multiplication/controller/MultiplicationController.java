package com.pkc.socialmultiplication.book.multiplication.controller;

import com.pkc.socialmultiplication.book.multiplication.domain.Multiplication;
import com.pkc.socialmultiplication.book.multiplication.domain.MultiplicationResultAttempt;
import com.pkc.socialmultiplication.book.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/multiplications")
public class MultiplicationController {

    private final MultiplicationService  multiplicationService;

    @Autowired
    public MultiplicationController(final MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @GetMapping("/random")
    Multiplication getRandomMultiplication() {
        return multiplicationService.createRandomMultiplication();
    }

}

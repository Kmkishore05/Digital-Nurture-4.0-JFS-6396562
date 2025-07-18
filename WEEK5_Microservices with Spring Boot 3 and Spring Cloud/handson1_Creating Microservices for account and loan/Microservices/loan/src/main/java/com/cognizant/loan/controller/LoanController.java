package com.cognizant.loan.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @GetMapping("/{number}")
    public Map<String, Object> getLoan(@PathVariable String number) {
        return Map.of(
            "number", number,
            "type", "House",
            "loan", 400000000,
            "emi", 20000,
            "tenure", 9
        );
    }
}

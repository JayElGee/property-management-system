package com.tlz.propertymanagement.controller;

import com.tlz.propertymanagement.model.CalculatorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    @GetMapping("/add/{num3}")
    public Double add(@RequestParam("num") Double num, @RequestParam("num2") Double num2, @PathVariable("num3") Double num3) {
        return num + num2;
    }

    @GetMapping("/sub/{num}/{num2}")
    public Double subtract(@PathVariable("num") Double num, @PathVariable("num2") Double num2) {
        Double result = null;

        if (num > num2) {
            result = num - num2;
        } else {
            result = num2 - num;
        }
        return result;
    }

    @PostMapping("/mul")
    public ResponseEntity<Double> multiply(@RequestBody CalculatorDTO calculatorDTO) {
        Double result = null;
        result = calculatorDTO.getNum() * calculatorDTO.getNum2() * calculatorDTO.getNum3() * calculatorDTO.getNum4();
        ResponseEntity<Double> responseEntity = new ResponseEntity<Double>(result, HttpStatus.CREATED);
        return responseEntity;
    }
}

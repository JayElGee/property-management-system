package com.tlz.propertymanagement.controller;

import com.tlz.propertymanagement.model.PropertyDTO;
import com.tlz.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @PostMapping("/properties")
    public PropertyDTO saveProperty(@RequestBody PropertyDTO propertyDTO) {

        propertyService.saveProperty(propertyDTO);
        System.out.println(propertyDTO);
        return propertyDTO;
    }
}

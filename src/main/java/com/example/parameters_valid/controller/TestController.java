package com.example.parameters_valid.controller;

import com.example.parameters_valid.controller.model.request.RequestModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/demo",method = RequestMethod.POST)
    public String test(@RequestBody RequestModel requestModel){
        return "success";
    }
}

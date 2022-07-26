package com.example.parameters_valid.controller.model.request;

import com.example.parameters_valid.annotations.VerifyRule;
import com.example.parameters_valid.annotations.VerifyType;
import lombok.Data;

@Data
public class Test {

    @VerifyRule(type = VerifyType.BETWEEN,rule = "1,9",message = "demo 值不在有效范围内")
    private Integer demo;
}

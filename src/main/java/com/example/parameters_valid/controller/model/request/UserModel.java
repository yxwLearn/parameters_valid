package com.example.parameters_valid.controller.model.request;

import com.example.parameters_valid.annotations.VerifyRule;
import com.example.parameters_valid.annotations.VerifyType;
import lombok.Data;

@Data
public class UserModel {

    @VerifyRule(type = VerifyType.HAS_TEXT)
    private String userName;

    @VerifyRule
    private Test test;
}

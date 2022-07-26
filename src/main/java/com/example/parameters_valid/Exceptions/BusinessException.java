package com.example.parameters_valid.Exceptions;

import java.lang.reflect.Field;

public class BusinessException extends RuntimeException {

    private int error = 201;

    public BusinessException(int error, String message) {
        super(message);
        this.error = error;
    }

    public BusinessException(String message) {
        super(message);
    }



}

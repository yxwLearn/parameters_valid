package com.example.parameters_valid.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RestControllerAdvice
@Slf4j
public class DefaultException {

    private Integer code;

    private String message;


    @ExceptionHandler({RuntimeException.class})
    public DefaultException exception(RuntimeException exception) {
        log.info("exception==============================",exception);
        if (exception instanceof BusinessException){
            return new DefaultException(-1,exception.getMessage());
        }
        return new DefaultException(-1,"系统内部错误");

    }
}

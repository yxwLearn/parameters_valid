package com.example.parameters_valid.annotations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;


@Slf4j
@Aspect
@Component
public class AspectHandler {



    //定义一个方法，用于声明切入表达式。
//    @Pointcut("@annotation(com.example.parameters_valid.annotations.VerifyRule)")
    @Pointcut(
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.PutMapping)"
    )
    public void validatorPointcut() {
    }

    @Before("validatorPointcut() ")
    public void fieldVerify(JoinPoint point) throws Exception {
        Object[] requestParams = point.getArgs();
        for (Object requestParamObj : requestParams) {
            Field[] declaredFields = requestParamObj.getClass().getDeclaredFields();
            // 遍历所有属性，并针对有@VerifyRule注解的属性进行校验
            for (Field field : declaredFields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(VerifyRule.class)) {
                    VerifyRule verify = field.getAnnotation(VerifyRule.class);
                    Object fieldObj = field.get(requestParamObj);
                    VerifyUtil.validate(verify,field,fieldObj);


                }
            }
        }
    }
}

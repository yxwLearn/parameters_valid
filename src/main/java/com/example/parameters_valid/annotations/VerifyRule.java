package com.example.parameters_valid.annotations;




import java.lang.annotation.*;

@Target( ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VerifyRule {

    VerifyType type() default VerifyType.DEFAULT;

    String rule() default "";

    boolean notNull() default true;

    String message() default "";

}

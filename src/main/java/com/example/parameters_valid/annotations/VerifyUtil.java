package com.example.parameters_valid.annotations;
import com.example.parameters_valid.Exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class VerifyUtil {

    public static void  validate(VerifyType type, String rule, boolean notNull, Object value, String message) throws BusinessException{
        System.out.println(type);
        System.out.println(rule);
        System.out.println(notNull);
        System.out.println(value);
        System.out.println(message);
        boolean valid = true;
        if(value == null || value == ""){
            valid = !notNull;
        }else{
            switch (type){

                case LENGTH:
                    valid = value instanceof String && value.toString().length() == Integer.parseInt(rule);
                    System.out.println(valid);
                    break;
                case BETWEEN:
                    String[] rules = rule.split(",");
                    if (value instanceof List){
                        List<?> list= (List<?>) value;
                        valid = list.size() > Integer.parseInt(rules[0]) && list.size() > Integer.parseInt(rules[1]);
                        break;
                    }else if (value instanceof Arrays){
                        Object[] arrays= (Object[]) value;
                        valid = arrays.length > Integer.parseInt(rules[0]) && arrays.length > Integer.parseInt(rules[1]);
                        break;
                    }else if (value instanceof Map){
                        Map map= (Map) value;
                        valid = map.size() > Integer.parseInt(rules[0]) && map.size() > Integer.parseInt(rules[1]);
                        break;
                    }else if (value instanceof Integer){
                        valid = Integer.parseInt(value.toString()) > Integer.parseInt(rules[0]) && Integer.parseInt(value.toString()) > Integer.parseInt(rules[1]);
                        break;
                    }

                case HAS_SIZE:
                    if (value instanceof List){
                        List<?> list= (List<?>) value;
                        valid = list.isEmpty();
                        break;
                    }else if (value instanceof Arrays){
                        Object[] arrays= (Object[]) value;
                        valid = arrays.length>0;
                        break;
                    }else if (value instanceof Map){
                        Map map= (Map) value;
                        valid = map.isEmpty();
                        break;
                    }
                case HAS_TEXT:
                    valid = StringUtils.isNotBlank(value.toString());
                    break;

            }
        }
        if (!valid){
            throw new BusinessException(StringUtils.isNotBlank(message) ? message:"请求参数不合法！");
        }
    }
}

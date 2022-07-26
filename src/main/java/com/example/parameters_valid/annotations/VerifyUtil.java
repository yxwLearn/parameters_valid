package com.example.parameters_valid.annotations;
import com.example.parameters_valid.Exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
public class VerifyUtil {

    public static void  validate(VerifyType type, String rule, boolean notNull, Object value, String message) throws BusinessException{

        String[] rules;
        boolean valid = true;
        if(value == null || value == ""){
            valid = !notNull;
        }else{
            switch (type){

                case LENGTH:
                    valid = value instanceof String && value.toString().length() == Integer.parseInt(rule);
                    break;
                case BETWEEN:
                    rules = rule.split(",");
                    if (value instanceof List){
                        List<?> list= (List<?>) value;
                        valid = list.size() >= Integer.parseInt(rules[0]) && list.size() <= Integer.parseInt(rules[1]);
                        break;
                    }else if (value instanceof Arrays){
                        Object[] arrays= (Object[]) value;
                        valid = arrays.length >= Integer.parseInt(rules[0]) && arrays.length <= Integer.parseInt(rules[1]);
                        break;
                    }else if (value instanceof Map){
                        Map map= (Map) value;
                        valid = map.size() >= Integer.parseInt(rules[0]) && map.size() <= Integer.parseInt(rules[1]);
                        break;
                    }else if (value instanceof Integer){
                        valid = Integer.parseInt(value.toString()) >= Integer.parseInt(rules[0]) && Integer.parseInt(value.toString()) <= Integer.parseInt(rules[1]);
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
                case REGEX:
                    valid =  Pattern.matches(rule,value.toString());
                    break;
                case IN:
                    rules = rule.split(",");
                    if (value instanceof Integer || value instanceof String){
                        valid = Arrays.asList(rules).contains(value.toString());;
                    }
                    break;
                default:
                    break;

            }
        }
        if (!valid){
            throw new BusinessException(StringUtils.isNotBlank(message) ? message:"请求参数不合法！");
        }
    }
}

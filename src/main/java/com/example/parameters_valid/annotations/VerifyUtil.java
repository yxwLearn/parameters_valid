package com.example.parameters_valid.annotations;
import cn.hutool.core.util.ObjectUtil;
import com.example.parameters_valid.Exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


@Slf4j
public class VerifyUtil {

    public static void  validate(VerifyRule verify, Field field, Object value) throws BusinessException, IllegalAccessException {
        String[] rules;
        boolean valid = true;
        if(value == null || value == ""){
            valid = !verify.notNull();
        }else{
            Class<?> clazz =value.getClass();
            // 判断为项目自定义类
            if (clazz.getName().startsWith("com.example.parameters_valid")){
                for (Field val : value.getClass().getDeclaredFields()) {
                    val.setAccessible(true);
                    if (val.isAnnotationPresent(VerifyRule.class)) {
                        VerifyRule verifyRule = val.getAnnotation(VerifyRule.class);
                        Object fieldObj = val.get(value);
                        VerifyUtil.validate(verifyRule,val,fieldObj);
                    }
                }
            }else{
                switch (verify.type()){

                    case LENGTH:
                        valid = value instanceof String && value.toString().length() == Integer.parseInt(verify.rule());
                        break;
                    case BETWEEN:

                        rules = verify.rule().split(",");
                        if (value instanceof List){
                            List<?> list= (List<?>) value;
                            valid = list.size() >= Integer.parseInt(rules[0]) && list.size() <= Integer.parseInt(rules[1]);
                            break;
                        }else if (value.getClass().isArray()){
                            Object[] arrays= (Object[])value;
                            valid = arrays.length >= Integer.parseInt(rules[0]) && arrays.length <= Integer.parseInt(rules[1]);
                            break;
                        }else if (value instanceof Map){
                            Map<?,?> map= (Map<?,?>) value;
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
                            Map<?,?> map= (Map<?,?>) value;
                            valid = map.isEmpty();
                            break;
                        }
                    case HAS_TEXT:
                        valid = StringUtils.isNotBlank(value.toString());
                        break;
                    case REGEX:
                        valid =  Pattern.matches(verify.rule(),value.toString());
                        break;
                    case IN:
                        rules = verify.rule().split(",");
                        if (value instanceof Integer || value instanceof String){
                            valid = Arrays.asList(rules).contains(value.toString());;
                        }
                        break;
                    default:
                        break;

                }

            }
        }
        if (!valid){
            throw new BusinessException(StringUtils.isNotBlank(verify.message()) ? verify.message():"请求参数"+field.getName()+"不合法！");
        }
    }
}

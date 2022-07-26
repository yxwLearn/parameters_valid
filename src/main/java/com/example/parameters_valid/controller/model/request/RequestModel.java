package com.example.parameters_valid.controller.model.request;

import com.example.parameters_valid.annotations.VerifyRule;
import com.example.parameters_valid.annotations.VerifyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestModel {

    @VerifyRule(type = VerifyType.LENGTH,rule="3",message = "长度不合法")
    private String userId;

    private String idCard;

    private String telephone;

    @VerifyRule(type = VerifyType.REGEX,rule="\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}",message = "邮箱格式错误")
    private String email;

    @VerifyRule(type = VerifyType.IN,rule="1,99",message = "非法值")
    private Integer age;

    @VerifyRule(type = VerifyType.BETWEEN,rule="1,3",message = "列表长度不合法")
    private List<String> list;

    @VerifyRule(type = VerifyType.BETWEEN,rule="1,3",message = "数组长度不合法")
    private String[] array;

    @VerifyRule(type = VerifyType.BETWEEN,rule="1,3",message = "map长度不合法")
    private Map<String,String> map;

    @VerifyRule
    private UserModel userModel;

    @VerifyRule
    private List<UserModel> userModelList;
}

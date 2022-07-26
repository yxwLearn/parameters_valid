package com.example.parameters_valid.controller.model.request;

import com.example.parameters_valid.annotations.VerifyRule;
import com.example.parameters_valid.annotations.VerifyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestModel {

    @VerifyRule(notNull = true,type = VerifyType.LENGTH,rule="3",message = "长度不合法")
    private String userId;

    private String idCard;

    private String telephone;

    private String email;

    private Integer age;

    private List<String> list;

    private List<UserModel> userModelList;
}

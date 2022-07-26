package com.example.parameters_valid.annotations;


public enum VerifyType {

        DEFAULT(""),

        LENGTH("指定长度，仅字符串生效"),

        BETWEEN("长度必须在指定范围内，仅集合，数组，map,字符串生效"),

        HAS_TEXT("必须有值，仅字符串生效"),

        HAS_SIZE("列表必须不为空，仅集合，数组,map生效"),

        REGEX("必须符合正则表达式"),

        IN("必须指定值范围内，仅字符串，整数生效");

        String typeModel;

        VerifyType(String typeModel){this.typeModel=typeModel;}

}

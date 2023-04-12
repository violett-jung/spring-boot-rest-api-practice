package com.study.restapipractice.data;

public enum RoleType {

    ADMIN("1"),
    USER("2");

    private final String code;

    private RoleType(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

}

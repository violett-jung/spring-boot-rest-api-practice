package com.study.restapipractice.data;

public enum Role {

    ADMIN("1"),
    USER("2");

    private final String code;

    private Role(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

}

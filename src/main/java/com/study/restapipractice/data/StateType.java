package com.study.restapipractice.data;

public enum StateType {

    ACTIVE("1"),
    INACTIVE("2");

    private final String code;

    private StateType(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}

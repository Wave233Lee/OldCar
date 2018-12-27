package com.example.oldcar.exception;

public class CarException extends RuntimeException {

    private Integer code;

    public CarException(EnumExceptions exceptionsEnum){
        super(exceptionsEnum.getMessage());
        this.code = exceptionsEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

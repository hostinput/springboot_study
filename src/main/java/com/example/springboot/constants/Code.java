package com.example.springboot.constants;

public enum Code {
    OK(200, "成功"), ERROR(400, "失败");


    private final String errorMsg;
    private final Integer code;

    Code(Integer code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Integer getCode() {
        return code;
    }
}

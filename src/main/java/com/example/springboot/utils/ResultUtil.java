package com.example.springboot.utils;

import com.example.springboot.constants.Code;
import com.example.springboot.entity.BaseResult;

public class ResultUtil {
    public static BaseResult ok(Object data) {
        return new BaseResult("成功", data, Code.OK);
    }

    public static BaseResult error(Object data, String errorMsg, Code code) {
        return new BaseResult(errorMsg, data, code);
    }

}

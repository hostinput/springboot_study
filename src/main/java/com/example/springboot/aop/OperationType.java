package com.example.springboot.aop;

public enum OperationType {
    /**
     * 操作类型
     */
    UNKNOWN("unknown"),//未知
    DELETE("delete"), //删除
    SELECT("select"), //查找
    UPDATE("update"), //更新
    INSERT("insert"); //插入

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    OperationType(String s) {
        this.value = s;
    }
}

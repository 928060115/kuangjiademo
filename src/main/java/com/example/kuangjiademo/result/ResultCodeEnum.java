package com.example.kuangjiademo.result;

/**
 * 返回状态码枚举类
 * @author liuyang
 * @since 2018/5/24 10:49
 */
public enum ResultCodeEnum {
    SUCCESS(200),
    ERROR(400),
    UNKNOW(-1);

    private Integer code;

    ResultCodeEnum(Integer code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}

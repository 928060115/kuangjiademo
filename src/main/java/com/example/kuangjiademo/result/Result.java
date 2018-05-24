package com.example.kuangjiademo.result;

import lombok.Getter;
import lombok.Setter;

/**
 * http请求返回对象统一封装
 * @author liuyang
 * @since 2018/5/24 10:40
 */
@Getter
@Setter
public class Result<T> {
    /**
     *  错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}

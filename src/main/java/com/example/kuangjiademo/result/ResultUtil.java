package com.example.kuangjiademo.result;

/**
 * @author liuyang
 * @since 2018/5/24 10:45
 */
public class ResultUtil {
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(String msg){
        Result result = new Result();
        result.setCode(ResultCodeEnum.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }
}

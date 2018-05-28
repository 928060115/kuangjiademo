package com.example.kuangjiademo.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;

/**
 * 所有工具类
 * @author liuyang
 * @since 2018/5/28 17:22
 */
@Slf4j
public class Utils {
    public static String checkVerify(HttpSession session, String code) {
        try{
            //从session中获取随机数
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null) {
                return "非法请求";
            }
            if (random.toLowerCase().equals(code.toLowerCase())) {
                return "true";
            } else {
                return "验证码输入错误";
            }
        }catch (Exception e){
            log.error("验证码校验失败{}", e);
            return "验证码校验失败";
        }
    }
}

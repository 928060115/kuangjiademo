package com.example.kuangjiademo.controller.verifyImg;

import com.example.kuangjiademo.utils.RandomValidateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuyang
 * @since 2018/5/24 15:41
 */
@Controller
@RequestMapping("/verify")
@Slf4j
public class VerifyController {

    @RequestMapping(value = "/getVerifyImg",method = RequestMethod.GET)
    public void getVerifyImg(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("img/jpeg");
            //设置响应头信息，告诉浏览器不要缓存此信息
            response.setHeader("Prama","No-cache");
            response.setHeader("Cache-Control","no-cache");
            response.setDateHeader("Expire",0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            // 输出验证码图片方法
            randomValidateCode.getRandcode(request,response);
        }catch (Exception e){
            log.error("获取验证码失败>>>>   ",e);
        }
    }
}

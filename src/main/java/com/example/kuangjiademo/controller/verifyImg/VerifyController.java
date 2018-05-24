package com.example.kuangjiademo.controller.verifyImg;

import com.example.kuangjiademo.result.Result;
import com.example.kuangjiademo.result.ResultCodeEnum;
import com.example.kuangjiademo.result.ResultUtil;
import com.example.kuangjiademo.utils.RandomValidateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

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

    /**
     * 忘记密码页面校验验证码
     */
    @RequestMapping(value = "/checkVerify", method = RequestMethod.POST)
    @ResponseBody
    public Result checkVerify(HttpSession session,@RequestBody Map<String,Object> params) {
        try{
            String inputStr = params.get("inputStr").toString();
            //从session中获取随机数
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null) {
                return ResultUtil.error(ResultCodeEnum.ERROR.getCode(),"非法请求");
            }
            if (random.equals(inputStr)) {
                return ResultUtil.error(ResultCodeEnum.SUCCESS.getCode(),"验证成功");
            } else {
                return ResultUtil.error(ResultCodeEnum.ERROR.getCode(),"验证码输入错误");
            }
        }catch (Exception e){
            log.error("验证码校验失败{}", e);
            return ResultUtil.error(ResultCodeEnum.ERROR.getCode(),"验证码校验失败");
        }
    }
}

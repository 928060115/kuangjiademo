package com.example.kuangjiademo.utils;

import lombok.extern.slf4j.Slf4j;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 实现图片验证码
 * @author liuyang
 * @since 2018/5/24 11:59
 */
@Slf4j
public class RandomValidateCodeUtil {

    public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";
    private String randString = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    /**
     * 图片宽
     */
    private int width = 95;
    /**
     * 图片高
     */
    private int height=30;
    /**
     * 干扰线数量
     * */
    private int lineSize=20;
    /**
     *随机产生字符数量
     */
    private int stringNum=4;
    
    private Random random = new Random();

    /**
     * 获得字体
     * @return
     */
    private Font getFont(){
        return new Font("Fixedsys",Font.CENTER_BASELINE,20);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc,int bc){
        if (fc > 255){
            fc = 255;
        }
        if (bc > 255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r,g,b);
    }

    /**
     * 生成随机图片
     */
    public void getRandcode(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //BufferedImage类是具有缓冲区的Image类，Image类是用于描述图像信息的类
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.fillRect(0,0,width,height);
        graphics.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,20));
        graphics.setColor(getRandColor(110,133));
        //绘制干扰线
        for(int i = 0;i<=lineSize;i++){
            drowLine(graphics);
        }
        //绘制随机字符
        String randomString = "";
        for (int i = 1;i <= stringNum;i++){
            randomString = drawString(graphics,randomString,i);
        }
        log.info("===========randomString========>>>>>{}",randomString);
        //将生成的随机字符串保存到session中
        session.removeAttribute(RANDOMCODEKEY);
        session.setAttribute(RANDOMCODEKEY,randomString);
        graphics.dispose();
        try {
            ImageIO.write(bufferedImage,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            log.error("将内存中的图片通过流动形式输出到客户端失败>>>>   ",e);
        }
    }

    /**
     * 绘制干扰线
     */
    private void drowLine(Graphics g){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int x1 = random.nextInt(13);
        int y1 = random.nextInt(15);
        g.drawLine(x,y,x1,y1);
    }

    /**
     *绘制字符串
     */
    private String drawString(Graphics g,String randomString,int i){
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101),random.nextInt(78),random.nextInt(121)));
        String rand = String.valueOf(getRandomcodeString(random.nextInt(randString.length())));
        randomString += rand;
        g.translate(random.nextInt(3),random.nextInt(6));
        g.drawString(rand,13*i,16);
        return randomString;
    }

    private String getRandomcodeString(int num){
        return String.valueOf(randString.charAt(num));
    }



    public static void main(String[] args) {
        System.out.println("RandomValidateCodekey".toUpperCase());
        System.out.println(new Random().nextInt(3));
        System.out.println(new RandomValidateCodeUtil().getRandomcodeString(5));
    }
}

package com.bookshop.config;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletException;

/**
 * 注册和配置kaptcha验证码servlet
 */
@Configuration
public class Kaptchaconfiguration{

    @Value("${kaptcha.border}")
    private String border;
    @Value("${kaptcha.textproducer.font.color}")
    private String fcolor;
    @Value("${kaptcha.image.width}")
    private String width;
    @Value("${kaptcha.textproducer.char.string}")
    private String cString;
    @Value("${kaptcha.image.height}")
    private String height;
    @Value("${kaptcha.textproducer.font.size}")
    private String fsize;
    @Value("${kaptcha.noise.color}")
    private String nColor;
    @Value("${kaptcha.textproducer.char.length}")
    private String clength;
    @Value("${kaptcha.textproducer.font.names}")
    private String fnames;
    @Bean
    public ServletRegistrationBean servletRegistrationBean() throws ServletException{
        ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(),"/Kaptcha");
        servlet.addInitParameter("kaptcha.border",border);//无边框
        servlet.addInitParameter("kaptcha.textproducer.font.color",fcolor);//字体颜色
        servlet.addInitParameter("kaptcha.image.width",width);//图片宽度
        servlet.addInitParameter("kaptcha.textproducer.char.string",cString);//使用哪些字
        servlet.addInitParameter("kaptcha.image.height",height);//图片高度
        servlet.addInitParameter("kaptcha.textproducer.font.size",fsize);//字体大小
        servlet.addInitParameter("kaptcha.noise.color",nColor);//干扰线的颜色
        servlet.addInitParameter("kaptcha.textproducer.char.length",clength);//字符个数
        servlet.addInitParameter("kaptcha.textproducer.font.names",fnames);//字体
        return servlet;
    }


}

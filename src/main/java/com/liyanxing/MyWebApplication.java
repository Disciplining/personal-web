package com.liyanxing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("com.liyanxing.**.mapper")
public class MyWebApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(MyWebApplication.class, args);
    }

    @Bean(name = "multipartConfigElement")
    public MultipartConfigElement multipartConfigElement()
    {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("1024MB"); //单个文件最大
        factory.setMaxRequestSize("4096MB"); //设置总上传数据总大小

        return factory.createMultipartConfig();
    }
}
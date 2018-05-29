package com.example.kuangjiademo.config;

import com.didispace.swagger.EnableSwagger2Doc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyang
 * @since 2018/5/24 10:07
 */
@Configuration
@EnableSwagger2Doc
public class Swagger2Config {
    @Bean
    public Docket createRestApi(){

        String auth = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOm51bGwsImNyZWF0ZWQiOjE1Mjc1OTEyMTAxNDksImV4cCI6MTUyNzU5MTMxMH0.C9kM04dEKU4_U8NekjESauHfwbar7vSBIXI7DOHX6K-oIso9VAaVRISExgakB7wHGNPkA98D35DKOTBhO0Ykxg";
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("Authorization").defaultValue(auth).description("i").modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalOperationParameters(aParameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.kuangjiademo.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("swagger2构建api文档")
                .description("简单优雅的restful风格，https://www.xyz128.cn")
                .termsOfServiceUrl("https://www.xyz128.cn")
                .version("v1.0")
                .build();
    }
}

package com.example.kuangjiademo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liuyang
 * @since 2018/5/24 10:14
 */
@SpringBootApplication
@MapperScan("com.example.kuangjiademo.mapper")
public class KuangjiademoApplication {
	public static void main(String[] args) {
		SpringApplication.run(KuangjiademoApplication.class, args);
	}
}

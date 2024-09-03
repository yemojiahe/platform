package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


// Generated by https://start.springboot.io
// 优质的 spring/boot/data/security/cloud 框架中文文档尽在 => https://springdoc.cn
//(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
//
@ComponentScan(basePackages = "com.example")
public class BootDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootDemoApplication.class, args);
	}

}

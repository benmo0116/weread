package com.wxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.wxy.mapper")//将项目中对应的mapper类的路径加进来就可以了

public class WereadApplication {

	public static void main(String[] args) {
		SpringApplication.run(WereadApplication.class, args);
	}
}

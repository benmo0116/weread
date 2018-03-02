package com.wxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.wxy.mapper")//将项目中对应的mapper类的路径加进来就可以了
@EnableCaching// 开启缓存，需要显示的指定
public class WereadApplication {

	public static void main(String[] args) {
		SpringApplication.run(WereadApplication.class, args);
	}
}

package com.zhaihuilin;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Log4j
@EnableWebSecurity   // 启动安全框架
@EnableCaching //启动缓存
@EnableTransactionManagement// 启动注解事务管理
@ImportResource(locations={"classpath:Kaptcher.xml"}) // 扫描验证码.xml
public class SpringBootSecurityApplication {

	public static void main(String[] args) {

		log.info("当项目启动报"+"No Spring Session store is configured: set the 'spring.session.store-type' property"+"的时候是因为:---------->"+"没有配置缓存类型" +"配置 RedisHttpSessionConfig");
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}
}

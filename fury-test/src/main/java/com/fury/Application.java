package com.fury;

//import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: pxp
 * @Description: 应用入口类
 * @CreateDate: 2018/1/15 10:54
 */
//@EnableSwagger2Doc //Swagger注解
//Spring Boot应用的标识，等同于 @Configuration @EnableAutoConfiguration @ComponentScanpublic
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableConfigurationProperties
//@ComponentScan(basePackages = {"com.fury.controller", "com.fury.service", "com.fury.listener"})
//@MapperScan(basePackages = {"com.fury.dao"}) //设置 Mapper 接口所在的包
public class Application {

    /**
     * main函数作为主入口。SpringApplication引导应用，并将Application本身作为参数传递给run方法。
     * 具体run方法会启动嵌入式的Tomcat并初始化Spring环境及其各Spring组件
     *
     * @param args
     */
    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
    }

}

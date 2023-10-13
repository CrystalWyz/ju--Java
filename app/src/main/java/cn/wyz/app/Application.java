package cn.wyz.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhouzhitong
 * @since 2023-10-09
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"cn.wyz"})
@MapperScan(basePackages = {"cn.wyz.*.mapper"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

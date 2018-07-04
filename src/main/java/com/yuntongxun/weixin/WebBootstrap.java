package com.yuntongxun.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by liugang on 2018/7/3.
 */

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableSwagger2
public class WebBootstrap {

    public static void main(String [] args){
        SpringApplication application = new SpringApplication(WebBootstrap.class);
        application.run(args);
    }
}

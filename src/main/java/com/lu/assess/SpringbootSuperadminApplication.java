package com.lu.assess;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.lu.assess.mapper")
@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
public class SpringbootSuperadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSuperadminApplication.class, args);
    }

}

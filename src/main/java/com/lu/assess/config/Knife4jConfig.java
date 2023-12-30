package com.lu.assess.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: 赵祖银
 * @CreateTime: 2022-10-08  16:12
 * @Description: cs
 * @Version: 1.0
 */


@Configuration
@EnableSwagger2
public class Knife4jConfig {

    /**
     * 通过knife4j生成 管理端 的接口文档
     * @return*/


    @Bean
    public Docket docketAdmin() {
//        log.info("准备生成管理端接口文档");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("教师人事考核项目接口文档\"")
                .version("1.0")
                .description("教师人事考核项目接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("admin接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lu.assess.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


}

package com.accp.Auctionafterend.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 创建REST风格API应用信息
     * 自定义apiInfo()方法完成自定义配置相关信息
     * 通过select()方法返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来显示，通常指向Action控制器包路径
     *
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()// 获取Docket中的选择器。 返回ApiSelectorBuilder。构建选择器的。如: 扫什么包的注解
//                .apis(Predicates.not( //取反。false -> truetrue -> false
//                        RequestHandlerSelectors.withMethodAnnotation(//当方法上有注解的时候返回true
//                                MyAnnotationSwagger.class)
//                ))
                .apis(RequestHandlerSelectors.basePackage("com.accp.Auctionafterend.boot.controller"))//设定扫描哪个包(包含子包)中的注解
                .paths(PathSelectors.any())
//                .paths(Predicates.or( //多个规则符合任意一个即可通过
//                        PathSelectors.regex("/swagger/.*"),
//                        PathSelectors.regex("/.*")
//                ))//使用正则表达式，约束生成API文档的路径
                .build();
    }

    /**
     * 创建该API的UI窗体信息
     * 访问地址：http://localhost:8080/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("XXX系统API测试信息浏览")
                .description("主要用来给调用者浏览API信息")
                .version("1.0")
                .contact(new Contact("XX发布 - Oid Jin", "http://2917219048.com", "admin@qq.com"))
                .build();
    }
}

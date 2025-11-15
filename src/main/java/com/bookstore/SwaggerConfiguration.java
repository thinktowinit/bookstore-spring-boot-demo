package com.bookstore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${swagger.title:}")
    private String title;

    @Value("${swagger.description:}")
    private String description;

    @Value("${swagger.version:}")
    private String version;

    @Value("${swagger.contact.name:}")
    private String contactName;

    @Value("${swagger.contact.url:}")
    private String contactUrl;

    @Value("${swagger.contact.email:}")
    private String contactEmail;

    @Bean
    public Docket api() {
        return new Docket(springfox.documentation.spi.DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bookstore.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .contact(new springfox.documentation.service.Contact(contactName, contactUrl, contactEmail))
                .build();
    }
}

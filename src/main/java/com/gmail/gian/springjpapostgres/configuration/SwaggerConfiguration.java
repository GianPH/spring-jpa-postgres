package com.gmail.gian.springjpapostgres.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by GianPH - 5/5/2018
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket gphApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gmail.gian.springjpapostgres"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        return new ApiInfo("GPH Test API",
                "This is GPH notification api documentation",
                "0.01",
                "use it wisely",
                new Contact("GianPH", "gianpurwah@gmail.com", "gianpurwah@gmail.com"),
                "Unknown",
                "Unknown",
                Collections.emptyList());
    }
}

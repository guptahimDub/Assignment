package com.fundsaccess.services.exchange;

import java.util.Collections;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class ForeignExchangeRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForeignExchangeRateApplication.class, args);
	}
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(Predicates.not(PathSelectors.regex("/error.*")))
          .build()
          .tags(new Tag("API", "Exchange Rate Service"))
		  .genericModelSubstitutes(Optional.class)
          .apiInfo(apiInfo());                                           
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Exchange Rate API", 
	      "This API provides the details about the Exchange Rates", 
	      null, 
	      null, 
	      new Contact("Himanshu", null, null), 
	      null, null, Collections.emptyList());
	}


}

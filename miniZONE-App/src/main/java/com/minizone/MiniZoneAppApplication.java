package com.minizone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class MiniZoneAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniZoneAppApplication.class, args);
	}
	
	@Bean
	LocalValidatorFactoryBean validate(MessageSource source) {
		
		LocalValidatorFactoryBean lvfb  = new LocalValidatorFactoryBean();
		lvfb.setValidationMessageSource(source);
	
		return lvfb;
	}	

}

package com.marketplace.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.marketplace.upload.StorageProperties;

@Configuration
@EnableJpaAuditing
@EnableConfigurationProperties(StorageProperties.class)
public class MyConfiguration {

	
//	@Bean 
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}

}

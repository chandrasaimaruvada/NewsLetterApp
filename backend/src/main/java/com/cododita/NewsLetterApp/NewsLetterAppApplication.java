package com.cododita.NewsLetterApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class NewsLetterAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsLetterAppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")  // Allow CORS for all paths
						.allowedOrigins("http://localhost:4200")  // Angular's default URL
						.allowedMethods("GET", "POST", "PUT", "DELETE")  // Allowing specific HTTP methods
						.allowedHeaders("*")  // Allow all headers
						.allowCredentials(true);  // Allow credentials (cookies, etc.)
			}
		};
	}
}

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("=".repeat(60));
		System.out.println("🚀 Dual BFF Application Started Successfully!");
		System.out.println("📱 Mobile BFF API: http://localhost:8080/api/mobile/v1");
		System.out.println("🌐 Web BFF API: http://localhost:8080/api/web/v1");
		System.out.println("❤️ Health Check: http://localhost:8080/api/mobile/v1/health");
		System.out.println("❤️ Health Check: http://localhost:8080/api/web/v1/health");
		System.out.println("📊 Actuator: http://localhost:8080/actuator/health");
		System.out.println("=".repeat(60));
	}
}

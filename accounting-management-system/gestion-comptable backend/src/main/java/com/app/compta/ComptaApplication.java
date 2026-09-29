package com.app.compta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComptaApplication {
    public static void main(String[] args) {
        DBConnection.getConnection();
        SpringApplication.run(ComptaApplication.class, args);
        System.out.println("🚀 Gestion Comptable Application started successfully!");
        System.out.println("📌 API available at: http://localhost:8080");
        System.out.println("📌 Authentication endpoints: http://localhost:8080/api/auth");
    }
}
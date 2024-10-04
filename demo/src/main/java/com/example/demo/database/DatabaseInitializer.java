//package com.example.demo.database;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//
////@Component
//public class DatabaseInitializer {
//
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//
//    @Value("${spring.datasource.username}")
//    private String dbUsername;
//
//    @Value("${spring.datasource.password}")
//    private String dbPassword;
//
//    @PostConstruct
//    public void init() {
//        String createDbSql = "CREATE DATABASE postgres";
//
//        // Используем DriverManager для создания соединения с PostgreSQL
//        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", dbUsername, dbPassword);
//             Statement statement = connection.createStatement()) {
//
//            // Выполните команду создания базы данных
//            statement.execute(createDbSql);
//            System.out.println("Database created successfully!");
//
//        } catch (SQLException e) {
//            System.err.println("Error creating database: " + e.getMessage());
//        }
//    }
//}
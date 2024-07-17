package com.lifung.todoitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.lifung.todoitem.repository")
public class TodoitemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoitemApplication.class, args);
    }
}
package com.forum_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
// @EnableCaching
@MapperScan("com.forum_system.dao")

public class ForumSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumSystemApplication.class, args);
    }

}

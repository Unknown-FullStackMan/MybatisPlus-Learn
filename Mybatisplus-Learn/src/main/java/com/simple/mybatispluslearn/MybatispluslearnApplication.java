package com.simple.mybatispluslearn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@MapperScan("com.simple.mybatispluslearn.mapper")
@SpringBootApplication

public class MybatispluslearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatispluslearnApplication.class, args);
    }

}

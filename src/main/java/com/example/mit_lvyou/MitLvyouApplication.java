package com.example.mit_lvyou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mit_lvyou.mapper")
public class MitLvyouApplication {

    public static void main(String[] args) {
        SpringApplication.run(MitLvyouApplication.class, args);
    }

}

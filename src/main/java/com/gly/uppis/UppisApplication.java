package com.gly.uppis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.gly.uppis.*.dao")
@SpringBootApplication
public class UppisApplication {

    public static void main(String[] args) {
        SpringApplication.run(UppisApplication.class, args);
    }

}

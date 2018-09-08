package com.api.open;

import com.api.open.mina.MinaServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.api.open.dao")
public class DatasoureApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatasoureApplication.class, args);
        MinaServer.startServer();
    }
}

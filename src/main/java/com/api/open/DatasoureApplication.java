package com.api.open;

import com.api.open.mina.MinaServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatasoureApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatasoureApplication.class, args);
        MinaServer.startServer();
    }
}

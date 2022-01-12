package com.example.postservice;

import com.example.postservice.api.ApiEndPoint;
import com.example.postservice.request.NumberRequest;
import com.example.postservice.response.NumberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PostServiceApplication {

    @Autowired
    ApiEndPoint<String, NumberRequest, NumberResponse,String> numberRequestEndpoint;

    public static void main(String[] args) {
        SpringApplication.run(PostServiceApplication.class, args);
    }

    @PostConstruct
    public void init(){
        numberRequestEndpoint.execute("userId1");
    }
}

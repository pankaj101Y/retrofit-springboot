package com.example.postservice.api;

import com.example.postservice.request.NumberRequest;
import com.example.postservice.response.NumberResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApiConfigurationNMS {

    private final NmsApiRepo nmsApiRepo = new ApiClientBuilder("http://localhost:8081/")
            .build(NmsApiRepo.class);

    @Bean
    ApiEndPoint<String, NumberRequest, NumberResponse, String> getNumberApiEndPoint() {
        return new
                ApiEndPoint<String, NumberRequest, NumberResponse, String>(
                s -> nmsApiRepo.getNumber(new NumberRequest(s), "myapp")
        )
                .setResponseBuilder(NumberResponse::getNumberResponse);
    }


}

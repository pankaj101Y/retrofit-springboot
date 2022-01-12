package com.example.postservice.api;


import com.example.postservice.request.NumberRequest;
import com.example.postservice.response.NumberResponse;
import retrofit2.Call;
import retrofit2.http.*;

interface NmsApiRepo {

    @GET("/getNumber")
    Call<NumberResponse> getNumber(@Query("userId") String userId);

    @POST("/getNumber")
    Call<NumberResponse> getNumber(@Body NumberRequest userId,@Query("source") String source);

}

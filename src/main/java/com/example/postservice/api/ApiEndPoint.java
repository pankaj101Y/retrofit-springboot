package com.example.postservice.api;

import com.example.postservice.builder.Builder;
import retrofit2.Call;

import java.util.function.Consumer;
import java.util.function.Function;

public class ApiEndPoint<Request,ApiRequest,ApiResponse, Response> {

    private final Function<Request,Call<ApiResponse>> callFunction;
    private Consumer<Response> errorChecker= response -> {};

    private  Builder<ApiResponse, Response> responseBuilder;
    private  Builder<Exception, Response> exceptionBuilder;

    public ApiEndPoint(Function<Request,Call<ApiResponse>> callFunction) {
        if (callFunction==null){
            throw new RuntimeException("Call function cannot be null");
        }
        this.callFunction = callFunction;
    }


    public ApiEndPoint<Request,ApiRequest,ApiResponse, Response> setResponseBuilder(Builder<ApiResponse, Response> responseBuilder) {
        this.responseBuilder = responseBuilder;
        return this;
    }

    public ApiEndPoint<Request,ApiRequest,ApiResponse, Response> setExceptionBuilder(Builder<Exception, Response> exceptionBuilder) {
        this.exceptionBuilder = exceptionBuilder;
        return this;
    }



    public ApiEndPoint<Request,ApiRequest,ApiResponse, Response> setErrorChecker(Consumer<Response> errorChecker) {
        this.errorChecker = errorChecker;
        return this;
    }

    public Response execute(Request request) {
        Response response=null;
        try {
            response= responseBuilder.build(callFunction.apply(request).execute().body());
        }catch (Exception e){
            if (exceptionBuilder!=null){
                response= exceptionBuilder.build(e);
            }
        }

        if (errorChecker!=null){
            errorChecker.accept(response);
        }

        return response;
    }

}

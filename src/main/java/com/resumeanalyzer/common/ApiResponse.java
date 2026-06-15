package com.resumeanalyzer.common;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse<T>
{
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timeStamp;

    public static <T> ApiResponse<T> success(T data,String message){
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .message(message)
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .timeStamp(LocalDateTime.now())
                .build();
    }


}

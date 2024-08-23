package com.Reclaimr.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse {
    private HttpStatus status;
    private String message;
    private Object data;
}


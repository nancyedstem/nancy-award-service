package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Response {
    private final String message;
    private final int success;
}

package com.bharath.learning.socialmediablog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;
    private int status;
}

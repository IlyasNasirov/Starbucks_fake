package com.example.sturbucks_fake.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private String message;

    private String code;

    private LocalDateTime timestamp;
}

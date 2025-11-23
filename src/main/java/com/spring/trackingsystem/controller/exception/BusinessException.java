package com.spring.trackingsystem.controller.exception;

import com.spring.trackingsystem.config.Constant;
import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public static BusinessException buildNotFoundException(Class<?> entity, Long id) {
        return  BusinessException
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(Constant.NOT_FOUND)
                .build();
    }

    public static BusinessException buildNotFoundException(Class<?> entity, String id) {
        return BusinessException
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(Constant.NOT_FOUND)
                .build();
    }

    public static BusinessException buildNotFoundException(String message) {
        return BusinessException
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(message)
                .build();
    }

    public static BusinessException buildBadRequestException(String message) {
        return BusinessException
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(message)
                .build();
    }

    public static BusinessException buildUnAuthorizedException(String message) {
        return BusinessException
                .builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message(message)
                .build();
    }

}

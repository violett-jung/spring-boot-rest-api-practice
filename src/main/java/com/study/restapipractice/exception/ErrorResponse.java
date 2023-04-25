package com.study.restapipractice.exception;

import com.study.restapipractice.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now(); //로컬 컴퓨터의 현재 날짜와 시간 정보
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode){
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .code(errorCode.getHttpStatus().name())
                        .error(errorCode.name())
                        .message(errorCode.getMessage())
                        .build());
    }

    //@Valid 에러 발생 시
    //400번 에러 발생
    public static ResponseEntity<ErrorResponse> toResponseEntity(FieldError fieldError, ErrorCode errorCode){
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .code(errorCode.getHttpStatus().name())
                        .error(errorCode.name())
                        .message(fieldError.getField() + " : " + fieldError.getDefaultMessage())
                        .build());
    }



}

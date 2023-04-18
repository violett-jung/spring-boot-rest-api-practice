package com.study.restapipractice.exception;

import com.study.restapipractice.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now(); //로컬 컴퓨터의 현재 날짜와 시간 정보
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorcode){
        return ResponseEntity.status(errorcode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorcode.getHttpStatus().value())
                        .code(errorcode.getHttpStatus().name())
                        .error(errorcode.name())
                        .message(errorcode.getMessage())
                        .build());
    }
}

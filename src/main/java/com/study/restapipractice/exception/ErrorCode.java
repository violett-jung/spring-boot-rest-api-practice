package com.study.restapipractice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    //need003. id 중복 시 에러
    // 409 CONFLICT - 리소스의 현재상태와 요청작업간의 충돌 발생. 중복된 데이터
    ID_DUPLICATED(HttpStatus.CONFLICT, "중복된 아이디 입니다.");



    private HttpStatus httpStatus;
    private String message;
}

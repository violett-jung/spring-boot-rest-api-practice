package com.study.restapipractice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    //need003. id 중복 시 에러
    // 409 CONFLICT - 리소스의 현재상태와 요청작업간의 충돌 발생. 중복된 데이터
    ID_DUPLICATED(HttpStatus.CONFLICT, "중복된 아이디 입니다."),

    //404
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 회원이 없습니다."),

    //400 BAD_REQUEST - 클라이언트에서 전송한 요청이 서버에서 처리될 수 없는 잘못된 요청;
    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "");



    private HttpStatus httpStatus;
    private String message;
}

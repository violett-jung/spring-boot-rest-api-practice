package com.study.restapipractice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
/*전역적으로 예외 처리가능
* @Controller 붙은 컨트롤러에서 발생하는 예외 처리
* 프로젝트 당 하나만 관리하는 것이 권장됨(Selector 사용 시 성능 영향)*/
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());

        /* 추가공부
        * 메서드 체이닝: 메서드가 호출된 후에 자신의 객체를 반환함으로써, 다른 메서드 호출을 연결하여 사용가능
        * ResponseEntity 클래스에서 제공하는 빌더 메서드들은 대부분 ResponseEntity 빌더 자체를 반환하기 때문에 메서드 체이닝 가능
        * */

    }
}

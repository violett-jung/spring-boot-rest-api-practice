package com.study.restapipractice.exception;

import com.study.restapipractice.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestControllerAdvice
/*전역적으로 예외 처리가능
* @Controller 붙은 컨트롤러에서 발생하는 예외 처리
* 프로젝트 당 하나만 관리하는 것이 권장됨(Selector 사용 시 성능 영향)*/
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class) //AppException.class이 발생헀을 경우 예외처리
    public ResponseEntity<ErrorResponse> appExceptionHandler(AppException e){
        log.error("error : ", e.getErrorCode() );
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    //RuntimeException 발생했는데 예외처리 못한 경우
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("내부 서버 에러입니다.");

        /* 추가공부
        * 메서드 체이닝: 메서드가 호출된 후에 자신의 객체를 반환함으로써, 다른 메서드 호출을 연결하여 사용가능
        * ResponseEntity 클래스에서 제공하는 빌더 메서드들은 대부분 ResponseEntity 빌더 자체를 반환하기 때문에 메서드 체이닝 가능
        * */

    }

    //@Valid를 이용한 검증
    //@Valid를 통과하지 못하면 MethodArgumentNotValidException이 발생함
    //검증 어노테이션 별로 지정해놨던 메시지 응답
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        FieldError fieldError = fieldErrors.get(0);

        log.error("error " + fieldError.getField() + ":" + fieldError.getDefaultMessage(), fieldError.getDefaultMessage());
        return ErrorResponse.toResponseEntity(fieldError, ErrorCode.BAD_REQUEST_ERROR);

    }











}

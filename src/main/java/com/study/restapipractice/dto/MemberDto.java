package com.study.restapipractice.dto;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.study.restapipractice.data.RoleType;
import com.study.restapipractice.data.StateType;
import com.study.restapipractice.entity.MemberEntity;
import com.study.restapipractice.util.JsonUtil;
import com.study.restapipractice.validation.ValidationGroups.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.io.IOException;


@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString
@Builder
public class MemberDto {

    //01. db구축 - 데이터를 받아올 MemberDto 생성
    /* Entity 클래스에서는 검증 애너테이션을 사용X
    * Entity 클래스는 데이터베이스 테이블과 매핑되는 객체이므로, 테이블의 제약조건과 일치하는 애너테이션을 사용할 필요없음
    * DTO나 VO 등과 같은 데이터 전송 객체에서 검증 애너테이션을 사용하여 입력값의 유효성을 검사하고, 이후에 Entity 객체로 변환하여 DB에 저장
    */
    //접근권한,활성화는 해당 클래스를 만들고 멤버를 상수로 설정함: 값의 변경 없이 정해진 값만 사용


    /* 유효성 검증 오류시 에러메세지가 랜덤하게 나오는 것 관련
    * Spring MVC에서 유효성 검증 실패 시, 오류(FieldError) 정보는 발생한 순서대로 BindingResult 객체에 저장
    * BindingResult 객체에서 오류 정보를 가져올 때는 순서대로 가져오기 때문에, 오류 정보의 순서가 변경되는 경우가 발생할 수 있습니다.
    * 따라서, 어떤 필드의 오류 정보가 먼저 발생하는지에 따라 BindingResult 객체에서 오류(FieldError) 정보의 순서가 결정되며, 이에 따라 반환되는 ErrorResponse 객체의 내용이 달라질 수 있음
    * 이러한 문제를 해결하기 위해서는, 유효성 검증에서 오류가 발생한 필드의 우선순위를 정해주는 등의 방법을 사용하여,
    * 오류(FieldError) 정보의 순서가 일정하게 유지되도록 처리해야 합니다.
    * 예를 들어, 여러 개의 필드에서 동시에 유효성 검증이 수행될 경우,
    * 어떤 필드에서 오류가 발생하더라도 해당 필드의 오류 정보를 먼저 BindingResult 객체에 추가하도록 유효성 검증 로직을 구성할 수 있습니다.
    *
    * 대표적인 어노테이션으로는 @Order
    * @Order 어노테이션은 어노테이션이 적용된 요소들의 실행 순서를 지정할 때 사용됩니다.
    * 이를 활용하여, 여러 개의 필드에서 동시에 유효성 검증이 수행되는 경우,
    * 어떤 필드에서 오류가 발생하더라도 해당 필드의 오류 정보를 먼저 BindingResult 객체에 추가하도록 처리할 수 있습니다.
    * */

    private Long seq; //식별자

    @NotBlank(groups = NotBlankGroupId.class) //null,""," " 모두 안됨
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "ID는 영어 대소문자와 숫자만 가능합니다.", groups = PatternCheckGroupId.class)
    private String id; //id


    @NotBlank(groups = NotBlankGroupPw.class)
    private String pw; //pw


    @NotBlank(groups = NotBlankGroupName.class)
    private String name; //이름


    @NotBlank(groups = NotBlankGroupEmail.class)
    @Email(groups = EmailCheckGroup.class)
    private String email; //메일주소


//    @JsonDeserialize(using = JsonUtil.PhoneNumberDeserializer.class)
    private String hp; //(옵션)폰번호

    /* Spring Boot에서는 RequestBody로 전송된 JSON 데이터의 필드 값을 자동으로 매핑하여 객체로 변환해주는 기능을 제공
     * 이때, 기본적으로 Jackson 라이브러리를 사용하며, Jackson은 문자열을 해당 필드의 타입으로 자동 변환해주는 기능 가짐
     *  이때, JSON 데이터의 필드 값이 null이거나 빈 문자열("")일 경우 Jackson은 해당 필드를 객체의 필드에 null로 설정
     * "role": "1"과 같은 문자열이 RequestBody로 전송된 경우에도 Jackson이 자동으로 문자열을 Integer형으로 변환하여 User 객체의 role 필드에 할당 -> 따라서 에러발생하지 않음
     *
     * * 해당 필드가 Integer형이 아닌 다른 타입으로 지정되어 있거나, 문자열 값을 수동으로 변환해주어야 하는 경우에는
     * @JsonDeserialize 어노테이션 등을 사용하여 Jackson이 해당 값을 원하는 타입으로 변환하도록 설정할 수 있음
     *
     * * 예시 : role 필드에 "", null 값이 들어올 경우 0으로 들어오도록 하기
     * -> role @JsonDeserialize 어노테이션을 사용하여 해당 필드를 Integer 타입으로 변환하고, 동시에 default 값을 0으로 설정
     * @JsonDeserialize(using = JsonUtil.IntegerDeserializer.class)
     * private Integer role; //접근권한(1 관리자, 2 일반인)
     * */


    @NotNull(groups = NotNullGroupRole.class) //원래 null만 검증실패여야하는데 null,""," " 모두 검증실패(이유: Jackson의 자동 변환 기능때문이었음)
    @Min(value = 1, message = "접근 권한은 1 또는 2이어야 합니다.", groups = MinValueCheckGroupRole.class)
    @Max(value = 2, message = "접근 권한은 1 또는 2이어야 합니다.", groups = MaxValueCheckGroupRole.class)
    private Integer role; //접근권한(1 관리자, 2 일반인)

    @NotNull(groups = NotNullGroupRole.class)
    @Min(value = 1, message = "접근 권한은 1 또는 2이어야 합니다.", groups = MinValueCheckGroupState.class)
    @Max(value = 2, message = "접근 권한은 1 또는 2이어야 합니다.", groups = MaxValueCheckGroupState.class)
    private Integer state; //활성화(1 활성화, 2 비활성화)

    private String description; //(옵션)설명

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .id(this.id)
                .pw(this.pw)
                .name(this.name)
                .email(this.email)
                .hp(this.hp)
                .role(this.role)
                .state(this.state)
                .description(this.description)
                .build();
    }
}

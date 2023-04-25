package com.study.restapipractice.dto;

import com.study.restapipractice.data.RoleType;
import com.study.restapipractice.data.StateType;
import com.study.restapipractice.entity.MemberEntity;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;


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

    private Long seq; //식별자

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "ID는 영어 대소문자와 숫자만 가능합니다.")
    private String id; //id

    @NotEmpty
    private String pw; //pw

    @NotEmpty
    private String name; //이름

    @NotNull
    @Email
    private String email; //메일주소

//    @Pattern.List({
//            @Pattern(regexp = "^$"), // 빈 문자열 허용
//            @Pattern(regexp = "^01([0|1|6|7|8|9])(\\d{3,4})(\\d{4})$", message = "핸드폰 번호는 010-1234-5678 형식으로 입력해주세요.")
//    })
    private String hp; //(옵션)폰번호

    @NotNull
    @Min(value = 1, message = "접근 권한은 1 또는 2이어야 합니다.")
    @Max(value = 2, message = "접근 권한은 1 또는 2이어야 합니다.")
    private Integer role; //접근권한(1 관리자, 2 일반인)

    @NotNull
    @Min(value = 1, message = "활성화는 1 또는 2이어야 합니다.")
    @Max(value = 2, message = "활성화는 1 또는 2이어야 합니다.")
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

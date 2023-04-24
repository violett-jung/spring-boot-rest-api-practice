package com.study.restapipractice.entity;

import com.study.restapipractice.data.RoleType;
import com.study.restapipractice.data.StateType;
import com.study.restapipractice.dto.MemberDto;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString
@Builder
@Table(name = "account")
public class MemberEntity {

    /*
    * @NotNull : 반드시 값이 있어야 한다.
    * @NotEmpty : 반드시 값이 존재하고 길이 혹은 크기가 0보다 커야한다.
    * @NotBlank : 반드시 값이 존재하고 공백 문자를 제외한 길이가 0보다 커야 한다.
    * @Email : 올바른 형식의 이메일 주소여야 한다. (@가 들어가야한다.)
    * */

    //02. db구축 - DB 테이블과 매핑할 MemberEntity생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동생성
    private Long seq; //식별자

    @NotBlank
//    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "ID는 영어 대소문자와 숫자만 가능합니다.")
    private String id; //id

    @NotNull
    private String pw; //pw

    @NotNull
    private String name; //이름

    @NotNull
//    @Email
    private String email; //메일주소

//    @Pattern(regexp = "^01([0|1|6|7|8|9])(\\d{3,4})(\\d{4})$", message = "핸드폰 번호는 010-1234-5678 형식으로 입력해주세요.")
    private String hp; //(옵션)폰번호

    @NotNull
//    @Min(value = 1, message = "접근 권한은 1 또는 2이어야 합니다.")
//    @Max(value = 2, message = "접근 권한은 1 또는 2이어야 합니다.")
    private Integer role; //접근권한(1 관리자, 2 일반인)

    @NotNull
//    @Min(value = 1, message = "활성화는 1 또는 2이어야 합니다.")
//    @Max(value = 2, message = "활성화는 1 또는 2이어야 합니다.")
    private Integer state; //활성화(1 활성화, 2 비활성화)

    private String description; //(옵션)설명

    public MemberDto toDto(){
        return MemberDto.builder()
                .seq(this.seq)
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

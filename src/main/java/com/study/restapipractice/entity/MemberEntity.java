package com.study.restapipractice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({"seq", "id", "pw", "name", "email", "hp", "role", "state", "description"})
public class MemberEntity {

    /*
    * @NotNull : 반드시 값이 있어야 한다.
    * @NotEmpty : 반드시 값이 존재하고 길이 혹은 크기가 0보다 커야한다.
    * @NotBlank : 반드시 값이 존재하고 공백 문자를 제외한 길이가 0보다 커야 한다.
    * @Email : 올바른 형식의 이메일 주소여야 한다. (@가 들어가야한다.)
    * */

    //02. db구축 - DB 테이블과 매핑할 MemberEntity생성
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동생성
//    private Long seq; //식별자
//
//    private String id; //id
//    private String pw; //pw
//    private String name; //이름
//    private String email; //메일주소
//    private String hp; //(옵션)폰번호
//    private Integer role; //접근권한(1 관리자, 2 일반인)
//    private Integer state; //활성화(1 활성화, 2 비활성화)
//    private String description; //(옵션)설명

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("seq")
    private Long seq;

    @JsonProperty("id")
    private String id;

    @JsonProperty("pw")
    private String pw;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("hp")
    private String hp;

    @JsonProperty("role")
    private Integer role;

    @JsonProperty("state")
    private Integer state;

    @JsonProperty("description")
    private String description;


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

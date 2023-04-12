package com.study.restapipractice.entity;

import com.study.restapipractice.data.Role;
import com.study.restapipractice.data.StateType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString
@Table(name = "member")
public class MemberEntity {

    //02. db구축 - DB 테이블과 매핑할 MemberEntity생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동생성
    private Long seq; //식별자
    @NotNull
    private String id; //id
    @NotNull
    private String pw; //pw
    @NotNull
    private String name; //이름
    @NotNull
    private String email; //메일주소
    private String hp; //(옵션)폰번호
    private Role role; //접근권한(1 관리자, 2 일반인)
    private StateType state; //활성화(1 활성화, 2 비활성화)
    private String description; //(옵션)설명
}

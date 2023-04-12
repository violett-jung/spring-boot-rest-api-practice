package com.study.restapipractice.dto;

import com.study.restapipractice.data.RoleType;
import com.study.restapipractice.data.StateType;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {

    //01. db구축 - 데이터를 받아올 MemberDto 생성
    //접근권한,활성화는 enum 타입으로 생성: 정해진 값만 사용해야 함
    private Long seq; //식별자
    private String id; //id
    private String pw; //pw
    private String name; //이름
    private String email; //메일주소
    private String hp; //(옵션)폰번호
    private Integer role; //접근권한(1 관리자, 2 일반인)
    private Integer state; //활성화(1 활성화, 2 비활성화)
    private String description; //(옵션)설명
}

package com.study.restapipractice.controller;

import com.study.restapipractice.dto.MemberDto;

import com.study.restapipractice.entity.MemberEntity;
import com.study.restapipractice.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class MemberController {

    /* 03. 기능구현
    * 03-1. get:  회원목록조회 및 회원조회
    * 03-2. post : 로그인
    * 03-3. post : 회원등록
    * 03-4. put : 회원수정
    * 03-5. delete : 회원삭제
    * 03-6. get: 회원목록다운로드
    * */

    @Autowired
    private MemberRepository memberRepository;

    //03-1. get:  회원목록조회 및 회원조회
    //03-1-1. 회원목록조회
    @GetMapping("/account")
    public List<MemberEntity> getMembers(){
        List<MemberEntity> members = memberRepository.findAll();
        log.info(members.toString());
        return members;
    }


    //03-2. post : 로그인
    //03-3. post : 회원등록
    //03-4. put : 회원수정
    //03-5. delete : 회원삭제
    //03-6. get: 회원목록다운로드

}

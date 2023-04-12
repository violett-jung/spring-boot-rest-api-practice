package com.study.restapipractice.controller;

import com.study.restapipractice.dto.MemberDto;

import com.study.restapipractice.entity.MemberEntity;
import com.study.restapipractice.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    //03-1-2. 회원조회
    //need: 예외처리-id에 해당하는 회원이 없는 경우
    @GetMapping("/account/{id}")
    public MemberEntity getMember(@PathVariable("id") Long seq){
        MemberEntity memberEntity = memberRepository.findById(seq).get();
        return memberEntity;
    }

    //03-2. post : 로그인
    //03-3. post : 회원등록
    //03-4. put : 회원수정
    //03-5. delete : 회원삭제
    //03-6. get: 회원목록다운로드

}

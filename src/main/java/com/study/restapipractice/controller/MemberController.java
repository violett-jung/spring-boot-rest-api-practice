package com.study.restapipractice.controller;

import com.study.restapipractice.dto.MemberDto;

import com.study.restapipractice.entity.MemberEntity;
import com.study.restapipractice.repository.MemberRepository;
import com.study.restapipractice.service.MemberService;
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

    /* 03. 기능구현 - a.컨트롤러단
    * 03-1. get:  회원목록조회 및 회원조회
    * 03-2. post : 회원등록
    * 03-3. post : 로그인
    * 03-4. put : 회원수정
    * 03-5. delete : 회원삭제
    * 03-6. get: 회원목록다운로드
    * */

    //임시로 컨트롤러에서 repository 사용, 추후 없앨예정
    @Autowired
    private MemberRepository memberRepository;

    //service 단계 추가
    private MemberService memberService;
    //의존성주입-생성자추가방식
    /* 이방식 대신 @RequiredArgsConstructor 사용해도 됨
     * 초기화 되지않은 final 필드, @NonNull 이 붙은 필드에 대해 생성자를 생성
     * @Autowired를 사용하지 않고 의존성 주입 가능
     * -> private final MemberService memberService; //이처럼 final 처리해야함
     */
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    //03-1. get:  회원목록조회 및 회원조회
    //03-1-1. 회원목록조회
    //need. 예외처리-회원이 없는 경우,서버에러
    @GetMapping("/account")
    public List<MemberEntity> getMembers(){
        List<MemberEntity> members = memberRepository.findAll();
        log.info(members.toString());

        return members;
    }

    //03-1-2. 회원조회
    //need. 예외처리-id에 해당하는 회원이 없는 경우,서버에러
    @GetMapping("/account/{id}")
    public MemberEntity getMember(@PathVariable("id") Long seq){
        MemberEntity memberEntity = memberRepository.findById(seq).get();
        return memberEntity;
    }

    //03-2. post : 회원등록
    //회원등록 만들면서 controller에서 전부 처리하던 service, repository 단계별 구분
    @PostMapping("/account")
    public ResponseEntity<?> createMember(@RequestBody MemberDto memberDto){
        memberService.registerMember(memberDto);
        return null;
    }

    //03-3. post : 로그인


    //03-4. put : 회원수정
    //03-5. delete : 회원삭제
    //03-6. get: 회원목록다운로드

}

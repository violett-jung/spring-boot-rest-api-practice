package com.study.restapipractice.controller;

import com.study.restapipractice.converter.CsvConverter;
import com.study.restapipractice.dto.LoginRequest;
import com.study.restapipractice.dto.MemberDto;

import com.study.restapipractice.entity.MemberEntity;
import com.study.restapipractice.exception.ErrorCode;
import com.study.restapipractice.exception.ErrorResponse;
import com.study.restapipractice.repository.MemberRepository;
import com.study.restapipractice.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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
    //need001~need011
    //002번까지 처리

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
    @GetMapping("/account")
    public ResponseEntity<List<MemberDto>> getMembers(){
        List<MemberDto> memberDtoList = memberService.findMembers();
        return ResponseEntity.status(HttpStatus.OK).body(memberDtoList);
    }

//    //기존코드
//    @GetMapping("/account")
//    public List<MemberEntity> getMembers(){
//        List<MemberEntity> members = memberRepository.findAll();
//        log.info(members.toString());
//
//        return members;
//    }

    //03-1-2. 회원조회
    @GetMapping("/account/{id}")
    public ResponseEntity<MemberDto> getMember(@PathVariable("id") Long seq){
        MemberDto memberDto = memberService.findMember(seq);
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

//    //기존코드
//    @GetMapping("/account/{id}")
//    public MemberEntity getMember(@PathVariable("id") Long seq){
//        MemberEntity memberEntity = memberRepository.findById(seq).get();
//        return memberEntity;
//    }

    //03-2. post : 회원등록
    //회원등록 만들면서 controller에서 전부 처리하던 service, repository 단계별 구분
    @PostMapping("/account")
    public ResponseEntity<?> createMember(@Valid @RequestBody MemberDto memberDto,  BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
//            return ResponseEntity.badRequest().body(fieldError.getDefaultMessage());
            return ErrorResponse.toResponseEntity(fieldError, ErrorCode.BAD_REQUEST_ERROR);
        }
        MemberDto savedMember = memberService.registerMember(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    //03-3. post : 로그인
    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody LoginRequest loginRequest){
        log.info(loginRequest.toString());
        MemberDto memberDto = memberService.authenticateMember(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    //03-4. put : 회원수정 //고쳐야함
    @PutMapping("/account/{id}")
    public ResponseEntity<?> updateMember(@PathVariable("id") Long seq, @RequestBody MemberDto memberDto){
        memberService.modifyMember(seq, memberDto);
        return null;
    }

    //03-5. delete : 회원삭제
    @DeleteMapping("/account/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") Long seq){
        memberService.removeMember(seq);
        return null;
    }

    //03-6. get: 회원목록다운로드
    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> downloadCsvBinary() throws IOException {
        byte[] csvBytes = memberService.exportMembersToBinary();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=members.csv");
        return ResponseEntity.ok().headers(headers).body(csvBytes);
    }
}

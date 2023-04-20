package com.study.restapipractice.service;

import com.study.restapipractice.dao.MemberDao;
import com.study.restapipractice.dto.LoginRequest;
import com.study.restapipractice.dto.MemberDto;
import com.study.restapipractice.entity.MemberEntity;
import com.study.restapipractice.exception.AppException;
import com.study.restapipractice.exception.ErrorCode;
import com.study.restapipractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor //DI 생성자주입
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberDao memberDao;

    /* 03. 기능구현 - b.서비스단
     * 03-1. find : 회원목록조회 및 회원조회
     * 03-2. register : 회원등록
     * 03-3. authenticate : 로그인
     * 03-4. modify : 회원수정
     * 03-5. remove : 회원삭제
     * 03-6. download : 회원목록다운로드
     * */

    //03-1. find : 회원목록조회 및 회원조회

    //03-2. register : 회원등록
    //need001. 예외처리-id가 중복되면 안됨
    //need002. 예외처리-옵션 제외 항목들은 반드시 notnull
    public MemberDto registerMember(MemberDto memberDto){
        log.info(memberDto.toString());
        MemberEntity memberEntity = memberDto.toEntity();

        //need003. id 중복 처리 -> error
        memberRepository.findById(memberEntity.getId())
                .ifPresent(member -> {
                    throw new AppException(ErrorCode.ID_DUPLICATED);
                });
        log.info(memberEntity.toString());

        //need004. null 체크(옵션 제외)

        //예외처리 통과 시 db저장: dao -> repository
        MemberEntity saved = memberDao.registerMember(memberEntity);
        log.info(saved.toString());
        return null;
    }

    //03-3. authenticate : 로그인
    //아이디와 비밀번호를 모두 입력한다고 가정(하나만 입력시 프론트단에서 못하도록 처리해야함)
    //need005. 존재하지 않는 아이디
    //need006. 아이디와 비밀번호가 일치하지 않을 때
    public MemberDto authenticateMember(LoginRequest loginRequest) {
        log.info(loginRequest.toString());

        //예외처리
        Optional<MemberEntity> matchingMember = memberRepository.findByIdAndPw(loginRequest.getUserId(), loginRequest.getUserPw());
        log.info(matchingMember.toString());

        //예외처리 통과시 db에서 확인
        MemberEntity loginMember = matchingMember.get();
        log.info(loginMember.toString());
        return null;


    }
    //03-4. modify : 회원수정
    //03-5. remove : 회원삭제
    //03-6. download : 회원목록다운로드



}

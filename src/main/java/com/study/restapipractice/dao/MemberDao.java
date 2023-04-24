package com.study.restapipractice.dao;

import com.study.restapipractice.entity.MemberEntity;
import com.study.restapipractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberDao {

    private final MemberRepository memberRepository;


    /* 03. 기능구현 - c.dao단
     * 03-1. find : 회원목록조회 및 회원조회
     * 03-2. register : 회원등록
     * 03-3. authenticate : 로그인
     * 03-4. modify : 회원수정
     * 03-5. remove : 회원삭제
     * 03-6. download : 회원목록다운로드
     * */

    //03-1. find : 회원목록조회 및 회원조회
    //03-1-1. 회원목록조회
    public List<MemberEntity> findMembers() {
        List<MemberEntity> members = memberRepository.findAll();
        log.info(members.toString());
        return members;
    }

    //03-1-2. 회원조회
    public Optional<MemberEntity> findMember(Long seq) {
        Optional<MemberEntity> member = memberRepository.findById(seq);
        log.info(member.toString());
        return member;
    }

    //03-2. register : 회원등록
    public MemberEntity registerMember(@Valid MemberEntity memberEntity) {
        MemberEntity saved = memberRepository.save(memberEntity);
        log.info(saved.toString());
        return saved;
    }

    //03-3. authenticate : 로그인
    public MemberEntity authenticateMember(MemberEntity authEntity){
       return null;
    };

    //03-4. modify : 회원수정
    public void modifyMember(MemberEntity modifyEntity) {
        MemberEntity saved = memberRepository.save(modifyEntity);
        log.info(saved.toString());

    }




    //03-5. remove : 회원삭제


    //03-6. download : 회원목록다운로드


}

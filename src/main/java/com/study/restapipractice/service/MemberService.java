package com.study.restapipractice.service;

import com.study.restapipractice.converter.CsvConverter;
import com.study.restapipractice.dao.MemberDao;
import com.study.restapipractice.dto.LoginRequest;
import com.study.restapipractice.dto.MemberDto;
import com.study.restapipractice.entity.MemberEntity;
import com.study.restapipractice.exception.AppException;
import com.study.restapipractice.exception.ErrorCode;
import com.study.restapipractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    //03-1-1. 회원목록조회
    //--need001. 회원목록이 없는 경우 처리 -> 200번 응답+빈 목록 반환 처리함
    public List<MemberDto> findMembers() {
        List<MemberEntity> foundMembers = memberDao.findMembers();

        //entity -> dto로 변경
        List<MemberDto> memberDtoList = new ArrayList<>();
        foundMembers.forEach(m -> {
                    memberDtoList.add(m.toDto());
                });
        log.info(memberDtoList.toString());

        return memberDtoList;
    }

    //03-1-2. 회원조회
    public MemberDto findMember(Long seq) {
        Optional<MemberEntity> member = memberDao.findMember(seq);
        log.info(member.toString());

        //--need002. 예외처리-id에 해당하는 회원이 없는 경우,서버에러(404)
        if(member.isEmpty()){
            throw new AppException(ErrorCode.MEMBER_NOT_FOUND);
        };

        //예외처리 통과 시
        //entity -> dto로 변경
        MemberDto memberDto = member.get().toDto();
        log.info(memberDto.toString());
        return memberDto;
    }


    //03-2. register : 회원등록
    //need003. 예외처리-id가 중복되면 안됨
    //need004. 예외처리-옵션 제외 항목들은 반드시 notnull
    public MemberDto registerMember(MemberDto memberDto){
        log.info(memberDto.toString());
        MemberEntity memberEntity = memberDto.toEntity();

        //need004. null 체크(옵션 제외) -> @Valid 이용한 검증

        //need003. id 중복 처리 -> error
        memberRepository.findById(memberEntity.getId())
                .ifPresent(member -> {
                    throw new AppException(ErrorCode.ID_DUPLICATED);
                });
        log.info(memberEntity.toString());

        //예외처리 통과 시 db저장: dao -> repository
        MemberEntity saved = memberDao.registerMember(memberEntity);
        log.info("saved: " + saved.toString());
        MemberDto savedDto = saved.toDto();
        return savedDto;
    }

    //03-3. authenticate : 로그인
    //아이디와 비밀번호를 모두 입력한다고 가정(하나만 입력시 프론트단에서 못하도록 처리해야함)
    //need005. 존재하지 않는 아이디
    //need006. 아이디와 비밀번호가 일치하지 않을 때
    public MemberDto authenticateMember(LoginRequest loginRequest) {

        //예외처리 ->아직안됨
        Optional<MemberEntity> matchingMember = memberRepository.findByIdAndPw(loginRequest.getId(), loginRequest.getPw());
        log.info(matchingMember.toString());

        //예외처리 통과시 db에서 확인
        MemberEntity loginMember = matchingMember.get();
        log.info(loginMember.toString());
        MemberDto loginDto = loginMember.toDto();
        return loginDto;


    }
    //03-4. modify : 회원수정
    public void modifyMember(Long id, MemberDto memberDto) {
        //1.수정용 엔티티로 변환
        MemberEntity modifyEntity = memberDto.toEntity();

        //2.대상 엔티티 조회
        Optional<MemberEntity> matchingMember = memberRepository.findById(id);
        MemberEntity memberEntity = matchingMember.get();
        log.info(memberEntity.toString());

        memberEntity.setName(modifyEntity.getName());
        //예외처리
        //need008. 존재하는 계정 아님
        //need009. pathvariable로 받은 seq와 수정용엔티티의 seq가 다를 경우
        //need007. memberDto에 완전한 정보 담기지 않음
        //need010. 입력하려는 데이터의 형식이 맞지 않을 때

        //3. 업데이트
        memberDao.modifyMember(memberEntity);
    }

    //03-5. remove : 회원삭제
    public void removeMember(Long seq) {
        //예외처리-id에 해당하는 회원이 없는 경우,서버에러(404)
        Optional<MemberEntity> member = memberDao.findMember(seq);
        log.info(member.toString());

        if(member.isEmpty()){
            throw new AppException(ErrorCode.MEMBER_NOT_FOUND);
        };

        //예외처리 통과 시
       memberDao.removeMember(member.get());
    }

    //03-6. download : 회원목록다운로드
    public byte[] exportMembersToBinary() throws IOException {
        List<MemberDto> members = findMembers();
        byte[] csvBytes = CsvConverter.convertToCsvBinary(members);
        return csvBytes;
    }



}

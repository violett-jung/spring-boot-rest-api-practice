package com.study.restapipractice.repository;

import com.study.restapipractice.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //쿼리 메소드 이용
    Optional<MemberEntity> findById(String id);
}

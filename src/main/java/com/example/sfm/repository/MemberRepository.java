package com.example.sfm.repository;

import com.example.sfm.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    boolean existsByName(String name);

    //JPA의 JpaRepository 매개변수, 인터페이스 명이 일치한 지 확인하면 된다.
    //
}

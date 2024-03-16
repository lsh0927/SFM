package com.example.sfm.repository;

import com.example.sfm.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    boolean existsByName(String name); // 이름이 중복되는지 확인하는 메소드

    Optional<Member> findByName(String name); // 이름으로 회원을 찾는 메소드
    //impl을 구현해서 Override해야함

    //이름으로 찾기
    //id로 찾기
    //list 반환 메서드 필요?
    //
}

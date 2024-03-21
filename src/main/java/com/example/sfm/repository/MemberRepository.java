package com.example.sfm.repository;

import com.example.sfm.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByName(String name);
    Member findByEmail(String email);

    //Member updateSession(String instrument);
}

package com.example.sfm.repository;

import com.example.sfm.domain.JoinRequest;
import com.example.sfm.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinRequestRepository extends JpaRepository<JoinRequest, Long> {
    JoinRequest findJoinRequestsByRequestId(Long id);
}

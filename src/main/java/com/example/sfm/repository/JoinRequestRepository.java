package com.example.sfm.repository;

import com.example.sfm.domain.JoinRequest;
import com.example.sfm.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoinRequestRepository extends JpaRepository<JoinRequest, Long> {
    List<JoinRequest> findJoinRequestsById(Long id);
}

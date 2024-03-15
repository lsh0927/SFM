package com.example.sfm.repository;

import com.example.sfm.domain.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends JpaRepository<Band,Long> {
    boolean existsByName(String bandName);
}

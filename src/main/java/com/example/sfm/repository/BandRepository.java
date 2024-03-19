package com.example.sfm.repository;

import com.example.sfm.domain.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends JpaRepository<Band,Long> {

    List<Band> findByBandName(String bandName);
    List<Band> findAll();
}

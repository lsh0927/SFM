package com.example.sfm.repository;

import com.example.sfm.domain.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends JpaRepository<Band,Long> {

   Band findByBandName(String bandName);
    List<Band> findAll();

    @Query("SELECT b FROM Band b JOIN FETCH b.members WHERE b.bandId = :bandId")
    Band findBandWithMembers(@Param("bandId") Long bandId);
}

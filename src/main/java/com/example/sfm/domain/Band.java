package com.example.sfm.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Band {
    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "BAND_NAME")
    private String bandName;


    @OneToMany(mappedBy = "band")
    private List<Member> members= new ArrayList<>();
}

package com.example.sfm.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity@Getter@Setter
public class Band {
    @Id
    @GeneratedValue
    @Column(name = "bnad_id")
    private Long bandId;


    @Column(name = "band_name")
    private String bandName;


    @OneToMany(mappedBy = "band")
    private List<Member> members= new ArrayList<>();
}

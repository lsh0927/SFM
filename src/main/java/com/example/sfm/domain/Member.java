package com.example.sfm.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Entity
@Getter
@NoArgsConstructor@Setter

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 10)
    private String name;

    @Column(length = 20)
    private String session;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;


}

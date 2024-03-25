package com.example.sfm.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 10)
    private String name;

    @Column(length = 20)
    private String session;

    @Column(length = 50)
    private String email;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    //밴드에서의 역할: 밴드장, 밴드원
    @Enumerated(EnumType.STRING)
    private BandRole bandRole;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "request_id")
    private JoinRequest joinRequest;
}

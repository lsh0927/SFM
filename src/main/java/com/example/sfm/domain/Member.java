package com.example.sfm.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "members")
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String name;

    @Column(length = 20)
    private String session;

    @Column(length = 20)
    private String address;


    // 멤버가 있고, 세션이 있고, 벤드가 있고,
    // 어디서부터 어디까지가 도메인이야 ㅅㅂ
    // ERD 설계부터 제대로 해야함..
    // 공연장도 domain?
    // 멤버가 세션을 선택히면, 세션도 DB에 들어갈것


    //지역별 분류? 대학 별 분류? 어디서 부터 어디까지를 DB에 저장할 값으로 잡아야...
    //




}

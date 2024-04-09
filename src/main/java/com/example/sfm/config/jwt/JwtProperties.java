package com.example.sfm.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt") //프로퍼티 값 가져옴
public class JwtProperties {
    private String issuer;
    private String secretKey;
}

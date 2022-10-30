package com.demoauthapi.demoauthapi.entity;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@Table(name = "tbl_members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, name = "nick_name")
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @Column(nullable = true, name = "refresh_token")
    private String refreshToken;

    @CreatedDate
    @Column(name = "signup_date")
    private LocalDateTime signUpDate;

    protected Member() {}

    @Builder
    public Member(String nickName, String password, String phoneNumber, LocalDateTime signUpDate) {
        this.nickName = nickName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.signUpDate = signUpDate;
    }

    public void setRefreshToken(String refreshToken) {
        if (StringUtils.isBlank(refreshToken)) throw new InvalidParameterException("refreshToken is blank");
        this.refreshToken = refreshToken;
    }
}
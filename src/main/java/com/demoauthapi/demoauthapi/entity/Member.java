package com.demoauthapi.demoauthapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Member {
    @Id
    private Long id;
    @Column(unique = true)
    private String nickName;
}

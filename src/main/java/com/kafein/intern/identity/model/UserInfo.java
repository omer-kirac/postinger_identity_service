package com.kafein.intern.identity.model;

import com.kafein.intern.identity.enums.Gender;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "USER_INFO")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "BIO")
    private String bio;

    @Column(name = "BIRTHDATE")
    private LocalDate birthDate;

    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "FOLLOWING_COUNT")
    private long followingCount;

    @Column(name = "FOLLOWER_COUNT")
    private long followerCount;

}
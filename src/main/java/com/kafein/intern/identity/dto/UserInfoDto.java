package com.kafein.intern.identity.dto;

import com.kafein.intern.identity.enums.Gender;
import lombok.Data;


import java.time.LocalDate;

@Data
public class UserInfoDto {

    private Long id;

    private String fullName;

    private String bio;

    private LocalDate birthDate;

    private Gender gender;

    private long followingCount;

    private long followerCount;

}

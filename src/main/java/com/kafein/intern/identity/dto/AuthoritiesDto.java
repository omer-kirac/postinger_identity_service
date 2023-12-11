package com.kafein.intern.identity.dto;

import lombok.Data;

import javax.persistence.Column;
@Data
public class AuthoritiesDto {

    private Long id;
    private String username;
    private String description;

}

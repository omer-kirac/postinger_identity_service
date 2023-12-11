package com.kafein.intern.identity.dto;

import com.kafein.intern.identity.model.Role;
import com.kafein.intern.identity.model.UserInfo;
import lombok.Data;

import javax.persistence.*;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private UserInfoDto info;
    private RoleDto role;

}

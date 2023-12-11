package com.kafein.intern.identity.mapper;

import com.kafein.intern.identity.dto.RoleDto;
import com.kafein.intern.identity.model.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toRoleDTO(Role role);
    List<RoleDto> toRoleDTOs(List<Role> roles);
    Role toRole(RoleDto roleDto);


}

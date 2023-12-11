package com.kafein.intern.identity.service;


import com.kafein.intern.identity.dto.RoleDto;
import com.kafein.intern.identity.mapper.RoleMapper;
import com.kafein.intern.identity.model.Role;
import com.kafein.intern.identity.model.User;
import com.kafein.intern.identity.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public RoleDto saveOrUpdate(RoleDto roleDto) {
        Role role = roleMapper.toRole(roleDto);
        role = roleRepository.save(role);

        return roleMapper.toRoleDTO(role);
    }


    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    public List<RoleDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toRoleDTOs(roles);

    }

    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("User not found");
        });
        return roleMapper.toRoleDTO(role);
    }
}

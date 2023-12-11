package com.kafein.intern.identity.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.kafein.intern.identity.dto.AuthoritiesDto;
import com.kafein.intern.identity.mapper.AuthoritiesMapper;
import com.kafein.intern.identity.mapper.RoleMapper;
import com.kafein.intern.identity.model.Authorities;
import com.kafein.intern.identity.repository.AuthoritiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorityService {
    private final AuthoritiesMapper authoritiesMapper;
    private final AuthoritiesRepository authoritiesRepository;

     public AuthoritiesDto saveOrUpdate(AuthoritiesDto authoritiesDto) {
         Authorities authorities = authoritiesMapper.toAuthorities(authoritiesDto);
         authorities = authoritiesRepository.save(authorities);

        return authoritiesMapper.toAuthoritiesDTO(authorities);
    }


    public void delete(Long id) {
        authoritiesRepository.deleteById(id);
    }
    public List<AuthoritiesDto> findAll() {
         List<Authorities> authoritiesList = authoritiesRepository.findAll();
        return authoritiesMapper.toAuthoritiesDTOs(authoritiesList);

    }

    public AuthoritiesDto findById(Long id) {
        Authorities authorities = authoritiesRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return authoritiesMapper.toAuthoritiesDTO(authorities);
    }

}

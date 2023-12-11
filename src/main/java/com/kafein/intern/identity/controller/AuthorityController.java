package com.kafein.intern.identity.controller;

import com.kafein.intern.identity.dto.AuthoritiesDto;
import com.kafein.intern.identity.mapper.AuthoritiesMapper;
import com.kafein.intern.identity.model.Authorities;
import com.kafein.intern.identity.model.Role;
import com.kafein.intern.identity.model.User;
import com.kafein.intern.identity.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authority")
public class AuthorityController {
    private final AuthorityService authorityService;
    private final AuthoritiesMapper authoritiesMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<AuthoritiesDto> saveAuthorities(@RequestBody AuthoritiesDto authoritiesDto) {
        AuthoritiesDto dto = authorityService.saveOrUpdate(authoritiesDto);
        return  ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public void delete(@RequestParam(name = "id")Long id){
        authorityService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/find")
    public ResponseEntity<AuthoritiesDto> findById(@RequestParam(name = "id") Long id) {
        //Optional<Authorities> authorities = Optional.ofNullable(authorityService.findById(id));
        //AuthoritiesDto authoritiesDto = authoritiesMapper.toAuthoritiesDTO(authorities.get());

        return  ResponseEntity.ok(authorityService.findById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/find-all")
    public ResponseEntity <List<AuthoritiesDto>>findAll() {
        //List<AuthoritiesDto> authoritiesList = authoritiesMapper.toAuthoritiesDTOs(authorityService.findAll());

        return ResponseEntity.ok(authorityService.findAll());
                //ResponseEntity.ok(authorityService.findAll());
    }
}

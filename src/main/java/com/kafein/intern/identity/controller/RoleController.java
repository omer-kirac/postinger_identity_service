package com.kafein.intern.identity.controller;

import com.kafein.intern.identity.dto.RoleDto;
import com.kafein.intern.identity.mapper.RoleMapper;
import com.kafein.intern.identity.model.Role;
import com.kafein.intern.identity.model.User;
import com.kafein.intern.identity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN')")//User hesabından  kullanıcı  role kaydetme işleminde hata olursa kaldırabiliriz.Farklı çözüm gerekli.
    public ResponseEntity<RoleDto> save(@RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.saveOrUpdate(roleDto));
    }


    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void delete(@RequestParam(name = "id")Long id){
        roleService.delete(id);
    }

    @GetMapping("/find")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<RoleDto> findById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/find-all")
    public ResponseEntity<List<RoleDto>> findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }



}

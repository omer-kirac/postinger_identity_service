package com.kafein.intern.identity.controller;

import com.kafein.intern.identity.dto.UserDto;
import com.kafein.intern.identity.exception.ErrorResponse;
import com.kafein.intern.identity.exception.UserAlreadyExistsException;
import com.kafein.intern.identity.mapper.UserMapper;
import com.kafein.intern.identity.model.Post;
import com.kafein.intern.identity.model.User;
import com.kafein.intern.identity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/find-all")
    public ResponseEntity<List<UserDto>> findAll() {
        //List<UserDto> userList = userMapper.toUserDTOs(userService.findAll());
        return ResponseEntity.ok(userService.findAll());
    }
    private final UserService userService;

    private final UserMapper userMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/find")
    public ResponseEntity<UserDto> findById(@RequestParam(name = "id") Long id) {

        //Optional<User> user = Optional.ofNullable(userService.findById(id));
        //UserDto userDto = userMapper.toUserDTO(user.get());
        return ResponseEntity.ok(userService.findById(id));
    }



    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveOrUpdate(userDto));
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public void delete(@RequestParam(name = "id")Long id){
        userService.delete(id);
    }


    @PutMapping("/updateUser")
    @PreAuthorize("hasAnyAuthority('USER')")
    public String updateByUser(@RequestBody UserDto userDto){
        return userService.updateByUser(userDto);
    }
    @PutMapping("/admin/updateUser")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String updateByAdmin(@RequestBody UserDto userDto){
        return userService.updateByAdmin(userDto);
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        return new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
    }




}
package com.kafein.intern.identity.controller;

import com.kafein.intern.identity.dto.UserDto;
import com.kafein.intern.identity.mapper.UserMapper;
import com.kafein.intern.identity.model.User;
import com.kafein.intern.identity.request.UserRequest;
import com.kafein.intern.identity.service.UserService;
import com.kafein.intern.identity.util.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    UserMapper userMapper;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest loginRequest, HttpServletResponse response){
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
           );
       } catch (RuntimeException e) {
           throw new RuntimeException("Login failed");
       }

       final User userDetails = (User) userService.loadUserByUsername(loginRequest.getUserName());
       final String jwt = jwtTokenUtil.generateToken(userDetails);

        Cookie jwtCookie = new Cookie("jwtToken",jwt);
        jwtCookie.setMaxAge(3600);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

       return ResponseEntity.ok(jwt);

    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest registerRequest){
        log.info("b11111");
        if (userService.findByUsername(registerRequest.getUserName()) != null) {
            return new ResponseEntity<>("Username already in use.",HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userService.saveOrUpdate(userMapper.toUserDTO(user));
        return new ResponseEntity<>("User successfully registered",HttpStatus.CREATED);
    }
}

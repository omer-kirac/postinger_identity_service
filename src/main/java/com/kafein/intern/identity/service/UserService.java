package com.kafein.intern.identity.service;

import com.kafein.intern.identity.dto.RoleDto;
import com.kafein.intern.identity.dto.UserDto;
import com.kafein.intern.identity.exception.NoSuchUsersExistsException;
import com.kafein.intern.identity.exception.UserAlreadyExistsException;
import com.kafein.intern.identity.mapper.UserMapper;
import com.kafein.intern.identity.model.Post;
import com.kafein.intern.identity.model.User;
import com.kafein.intern.identity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PostServiceClient postServiceClient;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserDto saveOrUpdate(UserDto userDto) {
        validateUser(userDto);
        User user = userMapper.toUser(userDto);
        if (userDto.getId() == null){
            user.setPassword(encoder.encode(userDto.getPassword()));
        } else {
            User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                    () -> {throw new RuntimeException("User not found");}
            );
            user.setPassword(existingUser.getPassword());
        }

        user = userRepository.save(user);
        return userMapper.toUserDTO(user);
    }

    private void validateUser(UserDto userDto) {
        User existingUser = userRepository.findByEmail(userDto.getEmail());
        if(existingUser != null) throw new RuntimeException("Email already exists");
    }


    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDTOs(users);
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
             new NoSuchUsersExistsException( "NO USER PRESENT WITH ID" +  id));

        return userMapper.toUserDTO(user);
    }



    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public String updateByUser(UserDto userDto){
        User existingUser = userRepository.findById(userDto.getId()).orElse(null);
        if (existingUser == null) throw new NoSuchUsersExistsException("No Such User exists!");
        else{
            existingUser.setUsername(userDto.getUsername());
            existingUser.setPassword(userDto.getPassword());
            existingUser.setEmail(userDto.getEmail());
            existingUser = userRepository.save(existingUser);
            return "Record updated Successfuly" + userMapper.toUserDTO(existingUser);
        }
    }

    public User findByUsername(String username) {
        return  userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }

    public String updateByAdmin(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId()).orElse(null);
        if (existingUser == null) throw new NoSuchUsersExistsException("No Such User exists!");
        else{
            User user = userMapper.toUser(userDto);
            userRepository.save(user);
            return "Record updated Successfuly" + userMapper.toUserDTO(user);
        }
    }

    public Long findIdByUsername(String username){
        Long id = findByUsername(username).getId();
        return id;
    }
}
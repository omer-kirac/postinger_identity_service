package com.kafein.intern.identity.mapper;

import com.kafein.intern.identity.dto.UserDto;
import com.kafein.intern.identity.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface  UserMapper {
    UserDto toUserDTO(User user);
    List<UserDto> toUserDTOs(List<User> users);
    User toUser(UserDto userDto);

}

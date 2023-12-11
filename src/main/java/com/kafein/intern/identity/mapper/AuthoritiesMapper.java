package com.kafein.intern.identity.mapper;

import com.kafein.intern.identity.dto.AuthoritiesDto;
import com.kafein.intern.identity.model.Authorities;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AuthoritiesMapper {

    //AuthoritiesDto toDto(Authorities entity);

    //Authorities toEntity(AuthoritiesDto dto);

    AuthoritiesDto toAuthoritiesDTO(Authorities authorities);
    List<AuthoritiesDto> toAuthoritiesDTOs(List<Authorities> authorities);

    Authorities toAuthorities(AuthoritiesDto authoritiesDto);

}

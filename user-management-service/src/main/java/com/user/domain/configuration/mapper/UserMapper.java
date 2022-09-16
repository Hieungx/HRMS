package com.user.domain.configuration.mapper;

import com.user.app.dtos.UserDTO;
import com.user.domain.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = CommonMapper.class)
public abstract class UserMapper implements BaseMapper<UserDTO, User>  {

    @Override
    @InheritConfiguration
    @Named("userToEntity")
    public abstract User toEntity(UserDTO dto);

    @Override
    @InheritConfiguration
    @Named("userToDTO")
    public abstract UserDTO toDTO(User entity);

    @Override
    @IterableMapping(qualifiedByName = "userToDTO")
    public abstract List<UserDTO> toListDTO(List<User> entityList);

    @Override
    @IterableMapping(qualifiedByName = "userToEntity")
    public abstract List<User> toListEntity(List<UserDTO> dtoList);
}

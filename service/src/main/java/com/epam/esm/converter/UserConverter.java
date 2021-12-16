package com.epam.esm.converter;

import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.UserEntity;

import java.util.List;

public interface UserConverter {
    UserDTO mapToDto(UserEntity userEntity);

    List<UserDTO> mapToDto(List<UserEntity> users);

    List<UserEntity> mapToEntity(List<UserDTO> users);

//    Iterable<UserDTO> mapToDto(Iterable<UserEntity> users);
//
//    Iterable<UserEntity> mapToEntity(Iterable<UserDTO> users);

    UserEntity mapToEntity(UserDTO userDTO);

}
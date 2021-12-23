package com.epam.esm.converter;

import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserConverter {
    UserDTO mapToDto(UserEntity userEntity);

    List<UserDTO> mapToDto(List<UserEntity> users);

    List<UserEntity> mapToEntity(List<UserDTO> users);

//    Page<UserDTO> mapToDto(Iterable<UserEntity> users);
//
//    Page<UserEntity> mapToEntity(Iterable<UserDTO> users);

    UserEntity mapToEntity(UserDTO userDTO);

}
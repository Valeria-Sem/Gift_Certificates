package com.epam.esm.converter.impl;

import com.epam.esm.converter.UserConverter;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public UserConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO mapToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    public List<UserDTO> mapToDto(List<UserEntity> users) {
        return users.stream().map(this::mapToDto).collect(Collectors.toList());

    }

    @Override
    public List<UserEntity> mapToEntity(List<UserDTO> users) {
        return users.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    @Override
    public UserEntity mapToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
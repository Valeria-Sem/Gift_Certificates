package com.epam.esm.service.impl;

import com.epam.esm.converter.UserConverter;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.UserEntity;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter converter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    @Override
    public Iterable<UserDTO> findAllUsers() {
        List<UserDTO> userDTOs;
        List<UserEntity> userEntities;

        userEntities = userRepository.findAll();
        userDTOs = converter.mapToDto(userEntities);

        return userDTOs;
    }

    @Override
    public UserDTO findUserById(Long id) {
        UserDTO userDTO;
        UserEntity userEntity;

        userEntity = userRepository.findUserEntityById(id);
        userDTO = converter.mapToDto(userEntity);

        return userDTO;
    }
}
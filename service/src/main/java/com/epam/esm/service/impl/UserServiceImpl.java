package com.epam.esm.service.impl;

import com.epam.esm.converter.UserConverter;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.UserEntity;
import com.epam.esm.repository.RepoException;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.UserService;
import com.epam.esm.service.validator.UserValidator;
import com.epam.esm.service.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter converter;
    private final UserValidator userValidator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserConverter converter,
                           UserValidator userValidator) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.userValidator = userValidator;
    }

    @Override
    public List<UserDTO> findAllUsers() throws ServiceException {
        List<UserDTO> userDTOs;
        List<UserEntity> userEntities;

        try{
            userEntities = userRepository.findAll();
            userDTOs = converter.mapToDto(userEntities);

            return userDTOs;
        } catch (Exception e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public UserDTO findUserById(Long id) throws ServiceException {
        UserDTO userDTO;
        UserEntity userEntity;

        try{
            userValidator.validateId(id);

            userEntity = userRepository.findUserEntityById(id);
            userDTO = converter.mapToDto(userEntity);

            return userDTO;
        } catch (RepoException | ValidatorException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }
}
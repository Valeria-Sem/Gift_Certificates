package com.epam.esm.service;


import com.epam.esm.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers () throws ServiceException;

    UserDTO findUserById(Long id) throws ServiceException;
}
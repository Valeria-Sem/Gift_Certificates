package com.epam.esm.service;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.UserDTO;

import java.util.List;

public interface OrderService {
    OrderDTO save(OrderDTO order) throws ServiceException;

    List<OrderDTO> getUserOrder(UserDTO user);

    OrderDTO getOrderById(Long id);
}

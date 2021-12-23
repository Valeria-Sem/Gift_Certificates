package com.epam.esm.service;

import com.epam.esm.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO save(OrderDTO order) throws ServiceException;

    List<OrderDTO> getUserOrder(long userId) throws ServiceException;

    OrderDTO getOrderById(Long id) throws ServiceException;
}

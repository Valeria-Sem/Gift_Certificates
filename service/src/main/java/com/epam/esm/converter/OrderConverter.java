package com.epam.esm.converter;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.entity.OrderEntity;

import java.util.List;

public interface OrderConverter {
    OrderDTO mapToDto(OrderEntity orderEntity);

    List<OrderDTO> mapToDto(List<OrderEntity> orders);

    List<OrderEntity> mapToEntity(List<OrderDTO> orders);

    OrderEntity mapToEntity(OrderDTO orderDTO);
}
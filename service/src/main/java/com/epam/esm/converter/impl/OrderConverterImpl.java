package com.epam.esm.converter.impl;

import com.epam.esm.converter.OrderConverter;
import com.epam.esm.dto.OrderDTO;
import com.epam.esm.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverterImpl implements OrderConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public OrderConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDTO mapToDto(OrderEntity orderEntity) {
        return modelMapper.map(orderEntity, OrderDTO.class);
    }

    @Override
    public List<OrderDTO> mapToDto(List<OrderEntity> orders) {
        return orders.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderEntity> mapToEntity(List<OrderDTO> orders) {
        return orders.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    @Override
    public OrderEntity mapToEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, OrderEntity.class);
    }
}
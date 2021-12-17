package com.epam.esm.service.impl;

import com.epam.esm.converter.OrderConverter;
import com.epam.esm.converter.UserConverter;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.OrderEntity;
import com.epam.esm.entity.UserEntity;
import com.epam.esm.repository.OrderRepository;
import com.epam.esm.service.*;
import com.epam.esm.service.validator.OrderValidator;
import com.epam.esm.service.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Time;

import java.time.LocalTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final UserService userService;
    private final GiftCertificateService giftCertificateService;
    private final OrderValidator orderValidator;
    private final WalletService walletService;
    private final UserConverter userConverter;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderConverter orderConverter,
                            UserService userService,
                            GiftCertificateService giftCertificateService,
                            OrderValidator orderValidator,
                            WalletService walletService,
                            UserConverter userConverter) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
        this.userService = userService;
        this.giftCertificateService = giftCertificateService;
        this.orderValidator = orderValidator;
        this.walletService = walletService;
        this.userConverter = userConverter;
    }

    @Override
    @Transactional
    public OrderDTO save(OrderDTO order) throws ServiceException {
        OrderDTO orderDTO;
        OrderEntity orderEntity;
        GiftCertificateDTO gift;
        UserDTO user;
        double balance;
        double price;

        try {
            user = userService.findUserById(order.getUser().getId());
            gift = giftCertificateService.getCertificateById(order.getGift().getId());

            price = gift.getPrice();
            balance = user.getWallet().getBalance();

            orderValidator.validateBalance(balance, price);
            balance -= price;
            user.getWallet().setBalance(balance);

            walletService.save(user.getWallet());

            order.setPrice(price);
            order.setTime(Time.valueOf(LocalTime.now()));
            order.setUser(user);
            order.setGift(gift);

            orderEntity = orderConverter.mapToEntity(order);
            orderEntity = orderRepository.saveAndFlush(orderEntity);

            orderDTO = orderConverter.mapToDto(orderEntity);

            return orderDTO;
        } catch (ValidatorException e) {
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public List<OrderDTO> getUserOrder(UserDTO user) {
        List<OrderDTO> orderDTOs;
        List<OrderEntity> orderEntities;
        UserEntity userEntity;

        userEntity = userConverter.mapToEntity(user);

        orderEntities = orderRepository.findOrderEntitiesByUser(userEntity);
        orderDTOs = orderConverter.mapToDto(orderEntities);

        return orderDTOs;
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        OrderDTO orderDTO;
        OrderEntity orderEntity;

        orderEntity = orderRepository.findOrderEntityById(id);
        orderDTO = orderConverter.mapToDto(orderEntity);

        return orderDTO;
    }
}

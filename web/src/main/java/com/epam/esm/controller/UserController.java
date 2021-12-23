package com.epam.esm.controller;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@ComponentScan
public class UserController {
    private final UserService userService;
    private final OrderService orderService;
    
    @Autowired
    public UserController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(@RequestParam(name = "offset") int offset,
                                                     @RequestParam(name = "limit") int limit) throws ServiceException {
        List<UserDTO> users = userService.findAllUsers();

        for(final UserDTO user : users){
            Link link = linkTo(methodOn(UserController.class)
                    .getUserById(user.getId())).withSelfRel();
            user.add(link);
        }

        CollectionModel<UserDTO> result = CollectionModel.of(users);

        Pageable pageable = PageRequest.of(offset - 1, limit);
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), users.size());

        Page<UserDTO> page = new PageImpl<>(users.subList(start, end), pageable, users.size());

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) throws ServiceException {
        UserDTO user = userService.findUserById(id);
        Link userLink = linkTo(methodOn(UserController.class)
                .getUserById(id)).withSelfRel();
        user.add(userLink);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/orders", produces = { "application/hal+json" })
    public ResponseEntity<Page<OrderDTO>> getOrders(@PathVariable(name = "id") Long id,
                                                    @RequestParam(name = "offset") int offset,
                                                    @RequestParam(name = "limit") int limit) throws ServiceException {
        List<OrderDTO> orders = orderService.getUserOrder(id);
        for(final OrderDTO order : orders){
            Link selfLink = linkTo(methodOn(OrderController.class)
                    .getOrderById(order.getId())).withSelfRel();
            order.add(selfLink);

            Link userLink = linkTo(methodOn(UserController.class)
                    .getUserById(order.getUser().getId())).withRel("userLink");
            order.add(userLink);

            Link giftLink = linkTo(methodOn(GiftCertificateController.class)
                    .getCertificateById(order.getGift().getId())).withRel("giftLink");
            order.add(giftLink);
        }

        Link link = linkTo(methodOn(UserController.class)
                .getOrders(id, offset, limit)).withSelfRel();
        CollectionModel<OrderDTO> result = CollectionModel.of(orders, link);

        Pageable pageable = PageRequest.of(offset - 1, limit);
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), orders.size());

        Page<OrderDTO> page = new PageImpl<>(orders.subList(start, end), pageable, orders.size());

        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
package com.epam.esm.controller;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> save(@RequestBody OrderDTO order) throws ServiceException {
        OrderDTO newOrder = orderService.save(order);

        Link selfLink = linkTo(methodOn(OrderController.class)
                .getOrderById(newOrder.getId())).withSelfRel();
        newOrder.add(selfLink);
        Link userLink = linkTo(methodOn(UserController.class)
                .getUserById(newOrder.getUser().getId())).withRel("userLink");
        newOrder.add(userLink);
        Link giftLink = linkTo(methodOn(GiftCertificateController.class)
                .getCertificateById(newOrder.getGift().getId())).withRel("giftLink");
        newOrder.add(giftLink);

        return new ResponseEntity<>(newOrder, HttpStatus.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable(name = "id") Long id) throws ServiceException {
        OrderDTO order = orderService.getOrderById(id);

        Link selfLink = linkTo(methodOn(OrderController.class)
                .getOrderById(id)).withSelfRel();
        order.add(selfLink);
        Link userLink = linkTo(methodOn(UserController.class)
                .getUserById(order.getUser().getId())).withRel("userLink");
        order.add(userLink);

        Link giftLink = linkTo(methodOn(GiftCertificateController.class)
                .getCertificateById(order.getGift().getId())).withRel("giftLink");
        order.add(giftLink);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}

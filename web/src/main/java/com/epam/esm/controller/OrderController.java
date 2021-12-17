package com.epam.esm.controller;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return new ResponseEntity<>(newOrder, HttpStatus.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getUserById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }
}

package com.epam.esm.controller;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
    public ResponseEntity<Iterable<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getOrders(@RequestBody UserDTO userDTO) throws ServiceException {
        List<OrderDTO> orders = orderService.getUserOrder(userDTO);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
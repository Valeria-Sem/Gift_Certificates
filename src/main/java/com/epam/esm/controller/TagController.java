package com.epam.esm.controller;

import com.epam.esm.service.ServiceException;
import com.epam.esm.service.ServiceProvider;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    private TagService tagService;
    private ServiceProvider serviceProvider;

    @Autowired
    public TagController() {
        serviceProvider = ServiceProvider.getInstance();
        this.tagService = serviceProvider.getTagService();
    }

    @PostMapping
    public void save(@RequestBody String name) throws ServiceException {
        tagService.save(name);
    }
}
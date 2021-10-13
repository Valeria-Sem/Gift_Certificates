package com.epam.esm.controller;

import com.epam.esm.service.ServiceException;
import com.epam.esm.service.ServiceProvider;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/tag")
public class TagController {
//    @Autowired
    private TagService tagService;

    @Autowired
    public TagController(ServiceProvider serviceProvider) {
        this.tagService = serviceProvider.getTagService();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody String name) throws ServiceException {
        tagService.save(name);
    }
}

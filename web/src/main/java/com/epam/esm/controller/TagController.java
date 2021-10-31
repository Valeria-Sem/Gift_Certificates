package com.epam.esm.controller;

import com.epam.esm.dto.TagDTO;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public TagDTO save(@RequestBody TagDTO tag) throws ServiceException {
       return tagService.save(tag);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") int id) throws ServiceException {
        tagService.delete(id);
    }

    @GetMapping("/all")
    public List<TagDTO> getAllTags() throws ServiceException{
        return tagService.getAllTags();
    }
}

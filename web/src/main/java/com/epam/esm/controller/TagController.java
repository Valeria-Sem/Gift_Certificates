package com.epam.esm.controller;

import com.epam.esm.dto.TagDTO;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Valeria
 * This is TagController. All http requests related to tags will come here and be processed.
 */
@RestController
@RequestMapping("/api/tag")
public class TagController {

    /**
     * This is TagService field where the link to the corresponding service will be placed
     */
    private final TagService tagService;

    /**
     * This is GiftCertificateController constructor for initialization of the class
     * @param tagService link to the corresponding service
     */
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Method for saving new Tag
     * @param tag object for saving into DB
     * @return TagDTO object
     * @throws ServiceException if something goes wrong will be thrown
     */
    @PostMapping
    public TagDTO save(@RequestBody TagDTO tag) throws ServiceException {
       return tagService.save(tag);
    }

    /**
     * Method for deleting tags
     * @param id tag ID
     * @throws ServiceException if something goes wrong will be thrown
     */
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") int id) throws ServiceException {
        tagService.delete(id);
    }

    /**
     * Method for getting all Tags
     * @return List with all tags from DB
     * @throws ServiceException if something goes wrong will be thrown
     */
    @GetMapping
    public List<TagDTO> getAllTags() throws ServiceException{
        return tagService.getAllTags();
    }
}

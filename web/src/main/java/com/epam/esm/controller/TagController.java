package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     *
     * @param tagService link to the corresponding service
     */
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Method for saving new Tag
     *
     * @param tag object for saving into DB
     * @return TagDTO object
     * @throws ServiceException if something goes wrong will be thrown
     */
    @PostMapping
    public ResponseEntity<TagDTO> save(@RequestBody TagDTO tag) throws ServiceException {
        TagDTO newTag = tagService.save(tag);

        return new ResponseEntity<>(newTag, HttpStatus.valueOf(201));
    }

    /**
     * Method for deleting tags
     *
     * @param id tag ID
     * @throws ServiceException if something goes wrong will be thrown
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") int id) throws ServiceException {
        tagService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method for getting all Tags
     *
     * @return List with all tags from DB
     * @throws ServiceException if something goes wrong will be thrown
     */
    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags() throws ServiceException {
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }
}

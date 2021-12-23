package com.epam.esm.controller;

import com.epam.esm.dto.TagDTO;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        Link selfLink = linkTo(methodOn(TagController.class)
                .getTagByName(newTag.getName())).withSelfRel();
        newTag.add(selfLink);

        return new ResponseEntity<>(newTag, HttpStatus.valueOf(201));
    }

    /**
     * Method for deleting tags
     *
     * @param id tag ID
     * @throws ServiceException if something goes wrong will be thrown
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) throws ServiceException {
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
    public ResponseEntity<Page<TagDTO>> getAllTags(@RequestParam(name = "offset") int offset,
                                                   @RequestParam(name = "limit") int limit) throws ServiceException {
        List<TagDTO> tags = tagService.getAllTags();

        for(final TagDTO tag : tags){
            Link link = linkTo(methodOn(TagController.class)
                    .getTagByName(tag.getName())).withSelfRel();
            tag.add(link);
        }

        CollectionModel<TagDTO> result = CollectionModel.of(tags);

        Pageable pageable = PageRequest.of(offset - 1, limit);
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), tags.size());

        Page<TagDTO> page = new PageImpl<>(tags.subList(start, end), pageable, tags.size());

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<TagDTO> getTagByName(@PathVariable(name = "name") String name) throws ServiceException {
        TagDTO tagDTO = tagService.getTagByName(name);
        Link link = linkTo(methodOn(TagController.class)
                .getTagByName(name)).withSelfRel();
        tagDTO.add(link);

        return new ResponseEntity<>(tagDTO, HttpStatus.OK);
    }

}

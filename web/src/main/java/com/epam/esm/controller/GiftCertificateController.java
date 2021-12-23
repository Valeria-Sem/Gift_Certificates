package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.ServiceException;
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
 * This is GiftCertificateController. All http requests related to certificates will come here and be processed.
 */
@RestController
@RequestMapping(path = "/api/giftCertificate")
public class GiftCertificateController {
    /**
     * This is GiftCertificateService field where the link to the corresponding service will be placed
     */
    private final GiftCertificateService giftCertificateService;

    /**
     * This is GiftCertificateController constructor for initialization of the class
     *
     * @param giftCertificateService link to the corresponding service
//     * @param giftTagService         link to the corresponding service
     */
    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
//        this.giftTagService = giftTagService;
    }

    /**
     * Method for saving new GiftCertificateDTO
     *
     * @param giftCertificateDTO object for saving into DB
     * @return GiftCertificateDTO object
     * @throws ServiceException if something goes wrong will be thrown
     */
    @PostMapping
    public ResponseEntity<GiftCertificateDTO> save(@RequestBody GiftCertificateDTO giftCertificateDTO) throws ServiceException {
        GiftCertificateDTO newCertificate = giftCertificateService.save(giftCertificateDTO);
        Link selfLink = linkTo(methodOn(GiftCertificateController.class)
                .getCertificateById(newCertificate.getId())).withSelfRel();
        newCertificate.add(selfLink);

        for(TagDTO tag : newCertificate.getTags()){
            Link tagLink = linkTo(methodOn(TagController.class)
                    .getTagByName(tag.getName())).withRel("tagLink");
            newCertificate.add(tagLink);
        }

        return new ResponseEntity<>(newCertificate, HttpStatus.valueOf(201));
    }

    /**
     * Method for updating GiftCertificateDTO
     * //  * @param request http link where stored json with parameters for updating
     *
     * @throws ServiceException if something goes wrong will be thrown
     */
    @PutMapping("/update")
    public ResponseEntity<GiftCertificateDTO> updateCertificate(@RequestBody GiftCertificateDTO certificate) throws ServiceException {
        GiftCertificateDTO gift = giftCertificateService.updateCertificate(certificate);
        Link selfLink = linkTo(methodOn(GiftCertificateController.class)
                .getCertificateById(gift.getId())).withSelfRel();
        gift.add(selfLink);

        for(TagDTO tag : gift.getTags()){
            Link tagLink = linkTo(methodOn(TagController.class)
                    .getTagByName(tag.getName())).withRel("tagLink");
            gift.add(tagLink);
        }

        return new ResponseEntity<>(gift, HttpStatus.valueOf(201));
    }

    /**
     * Method for getting all Gift Certificates
     *
     * @return List with all GiftCertificates
     * @throws ServiceException if something goes wrong will be thrown
     */
    @GetMapping
    public ResponseEntity<Page<GiftCertificateDTO>> getAllCertificates(@RequestParam(name = "offset") int offset,
                                                                       @RequestParam(name = "limit") int limit) throws ServiceException {
        List<GiftCertificateDTO> GiftCertificateDTOs = giftCertificateService.getAllCertificates();
        for(final GiftCertificateDTO gift : GiftCertificateDTOs){
            Link link = linkTo(methodOn(GiftCertificateController.class)
                    .getCertificateById(gift.getId())).withSelfRel();
            gift.add(link);

            for(TagDTO tag : gift.getTags()){
                Link tagLink = linkTo(methodOn(TagController.class)
                        .getTagByName(tag.getName())).withRel("tagLink");
                gift.add(tagLink);
            }
        }

        CollectionModel<GiftCertificateDTO> result = CollectionModel.of(GiftCertificateDTOs);

        Pageable pageable = PageRequest.of(offset - 1, limit);
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), GiftCertificateDTOs.size());

        Page<GiftCertificateDTO> page = new PageImpl<>(GiftCertificateDTOs.subList(start, end), pageable, GiftCertificateDTOs.size());

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftCertificateDTO> getCertificateById(@PathVariable(name = "id") Long id) throws ServiceException {
        GiftCertificateDTO certificate = giftCertificateService.getCertificateById(id);
        Link selfLink = linkTo(methodOn(GiftCertificateController.class)
                .getCertificateById(id)).withSelfRel();
        certificate.add(selfLink);

        for(TagDTO tag : certificate.getTags()){
            Link tagLink = linkTo(methodOn(TagController.class)
                    .getTagByName(tag.getName())).withRel("tagLink");
            certificate.add(tagLink);
        }


        return new ResponseEntity<>(certificate, HttpStatus.OK);
    }

    /**
     * Method for deleting GiftCertificate
     *
     * @param id certificate ID
     * @throws ServiceException if something goes wrong will be thrown
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGiftCertificate(@PathVariable(name = "id") Long id) throws ServiceException {
        giftCertificateService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method for search and sort among the certificates
     *
     * @param part part of certificate name
     * @param tag  name of tag
     * @param sort sort ASC/DESC
     * @return List of Certificates
     * @throws ServiceException if something goes wrong will be thrown
     */
//    @GetMapping(value = {"/search"})
//    public ResponseEntity<List<GiftCertificateDTO>> search(@RequestParam(name = "part", required = false) String part,
//                                                           @RequestParam(name = "tag", required = false) String tag,
//                                                           @RequestParam(name = "sort", required = false) String sort) throws ServiceException {
//        HashMap<String, String> properties = new HashMap<>();
//        properties.put("part", part);
//        properties.put("tag", tag);
//        properties.put("sort", sort);
//
//        List<GiftCertificateDTO> dtos = giftTagService.search(properties);
//
//        return new ResponseEntity<>(dtos, HttpStatus.OK);
//    }

    /**
     * Method for deleting relationship between certificate and tag
     *
     * @param id giftTag ID
     * @throws ServiceException if something goes wrong will be thrown
     */
//    @DeleteMapping("/giftTag/{id}")
//    public ResponseEntity<Void> delete(@PathVariable(name = "id") int id) throws ServiceException {
//        giftTagService.delete(id);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}

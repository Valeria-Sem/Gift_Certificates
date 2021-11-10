package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.GiftTagService;
import com.epam.esm.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author Valeria
 * This is GiftCertificateController. All http requests related to certificates will come here and be processed.
 */
@RestController
@RequestMapping( path = "/api/giftCertificate")
public class GiftCertificateController {
    /**
     * This is GiftCertificateService field where the link to the corresponding service will be placed
     */
    private final GiftCertificateService giftCertificateService;

    /**
     * This is GiftTagService field where the link to the corresponding service will be placed
     */
    private final GiftTagService giftTagService;

    /**
     * This is GiftCertificateController constructor for initialization of the class
     * @param giftCertificateService link to the corresponding service
     * @param giftTagService link to the corresponding service
     */
    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService, GiftTagService giftTagService){
        this.giftCertificateService = giftCertificateService;
        this.giftTagService = giftTagService;
    }

    /**
     * Method for saving new GiftCertificateDTO
     * @param giftCertificateDTO object for saving into DB
     * @return GiftCertificateDTO object
     * @throws ServiceException if something goes wrong will be thrown
     */
    @PostMapping
    public ResponseEntity<GiftCertificateDTO> save(@RequestBody GiftCertificateDTO giftCertificateDTO) throws ServiceException{
        GiftCertificateDTO newCertificate = giftCertificateService.save(giftCertificateDTO);

        return new ResponseEntity<>(newCertificate, HttpStatus.valueOf(201));
    }

    /**
     * Method for updating GiftCertificateDTO
   //  * @param request http link where stored json with parameters for updating
     * @throws ServiceException if something goes wrong will be thrown
     */
    @PostMapping("/update")
    public ResponseEntity<Void> updateCertificate(@RequestBody GiftCertificateDTO certificate) throws ServiceException{
        giftCertificateService.updateCertificate(certificate);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method for getting all Gift Certificates
     * @return List with all GiftCertificates
     * @throws ServiceException if something goes wrong will be thrown
     */
    @GetMapping
    public ResponseEntity<List<GiftCertificateDTO>> getAllCertificates() throws ServiceException{
        List<GiftCertificateDTO> GiftCertificateDTOs = giftCertificateService.getAllCertificates();
        return new ResponseEntity<>(GiftCertificateDTOs, HttpStatus.OK);
    }

    /**
     * Method for deleting GiftCertificate
     * @param id certificate ID
     * @throws ServiceException if something goes wrong will be thrown
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGiftCertificate(@PathVariable(name = "id") int id) throws ServiceException{
        giftCertificateService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method for search and sort among the certificates
     * @param part part of certificate name
     * @param tag name of tag
     * @param sort sort ASC/DESC
     * @return List of Certificates
     * @throws ServiceException if something goes wrong will be thrown
     */
    @GetMapping(value = {"/search"})
    public ResponseEntity<List<GiftCertificateDTO>> search(@RequestParam(name = "part", required = false) String part,
                                           @RequestParam(name = "tag", required = false) String tag,
                                           @RequestParam(name = "sort", required = false) String sort) throws ServiceException{
        HashMap properties = new HashMap<>();
        properties.put("part", part);
        properties.put("tag", tag);
        properties.put("sort", sort);

        return new ResponseEntity<>(giftTagService.search(properties), HttpStatus.OK);
    }

    /**
     * Method for deleting relationship between certificate and tag
     * @param id giftTag ID
     * @throws ServiceException if something goes wrong will be thrown
     */
    @DeleteMapping("/delete/giftTag/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") int id) throws ServiceException {
        giftTagService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.GiftTagService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.controller.util.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    public GiftCertificateDTO save(@RequestBody GiftCertificateDTO giftCertificateDTO) throws ServiceException{
       return giftCertificateService.save(giftCertificateDTO);
    }

    /**
     * Method for updating GiftCertificateDTO
     * @param request http link where stored json with parameters for updating
     * @throws ServiceException if something goes wrong will be thrown
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path ="/update")
    public void updateCertificate(HttpServletRequest request) throws ServiceException{
        JsonReader reader = new JsonReader();
        String json = null;

        if ("POST".equalsIgnoreCase(request.getMethod()))
        {
            try {
                json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        giftCertificateService.updateCertificate(reader.getObjectsFromJSON(json));
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
    public void deleteGiftCertificate(@PathVariable(name = "id") int id) throws ServiceException{
        giftCertificateService.delete(id);
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
    public List<GiftCertificateDTO> search(@RequestParam(name = "part", required = false) String part,
                                           @RequestParam(name = "tag", required = false) String tag,
                                           @RequestParam(name = "sort", required = false) String sort) throws ServiceException{
        HashMap properties = new HashMap<>();
        properties.put("part", part);
        properties.put("tag", tag);
        properties.put("sort", sort);

        return giftTagService.search(properties);
    }

    /**
     * Method for deleting relationship between certificate and tag
     * @param id giftTag ID
     * @throws ServiceException if something goes wrong will be thrown
     */
    @DeleteMapping("/delete/giftTag/{id}")
    public void delete(@PathVariable(name = "id") int id) throws ServiceException {
        giftTagService.delete(id);
    }
}

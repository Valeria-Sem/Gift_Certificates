package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.GiftTagDTO;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( path = "/api/giftCertificate")
public class GiftCertificateController {
    private final GiftCertificateService giftCertificateService;
    private final GiftTagService giftTagService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService, GiftTagService giftTagService){
        this.giftCertificateService = giftCertificateService;
        this.giftTagService = giftTagService;
    }

    @PostMapping
    public GiftCertificateDTO save(@RequestBody GiftCertificateDTO giftCertificateBean) throws ServiceException{
       return giftCertificateService.save(giftCertificateBean);
    }


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

    @GetMapping()
    public ResponseEntity<List<GiftCertificateDTO>> getAllCertificates() throws ServiceException{
        List<GiftCertificateDTO> dtos = giftCertificateService.getAllCertificates();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGiftCertificate(@PathVariable(name = "id") int id) throws ServiceException{
        giftCertificateService.delete(id);
    }

    @PostMapping("/assignTag")
    public GiftTagDTO assignTag(@RequestBody int giftId, @RequestBody String tagName) throws ServiceException{
        return giftTagService.save(giftId, tagName);
    }

    @GetMapping(value = {"/search/{part}",
            "/search/{part}/{sort}",
            "/search/{part}/byTag/{tag}",
            "/search/{part}/byTag/{tag}/{sort}",
            "/search/byTag/{tag}",
            "/search/byTag/{tag}/{sort}",
            "/{sort}"})
    public List<GiftCertificateDTO> search(@PathVariable(name = "part", required = false) String part,
                                           @PathVariable(name = "tag", required = false) String tag,
                                           @PathVariable(name = "sort", required = false) String sort) throws ServiceException{
        if(part != null && tag != null && sort != null){
            return giftTagService.searchAndSortByPartOfCertificateNameAndTag(part, tag, sort);

        } else if(part != null && tag != null){
            return giftTagService.searchByPartAndTag(part, tag);

        } else if(part != null && sort != null){
            return giftCertificateService.searchAndSortByPartOfCertificateName(part, sort);

        } else if(part != null){
            return giftCertificateService.searchByPartOfCertificateName(part);

        } else if(tag != null && sort != null){
            return giftTagService.searchAndSortByTag(tag, sort);

        } else if(tag != null){
            return giftTagService.getCertificatesByTagName(tag);

        } else if(sort != null){
            return giftCertificateService.sort(sort);
        }

        return giftCertificateService.getAllCertificates();
    }


    @DeleteMapping("/delete/giftTag/{id}")
    public void delete(@PathVariable(name = "id") int id) throws ServiceException {
        giftTagService.delete(id);
    }
}

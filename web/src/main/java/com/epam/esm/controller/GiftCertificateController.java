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

    @GetMapping("/all")
    public ResponseEntity<List<GiftCertificateDTO>> getAllCertificates() throws ServiceException{
        List<GiftCertificateDTO> dtos = giftCertificateService.getAllCertificates();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGiftCertificate(@PathVariable(name = "id") int id) throws ServiceException{
        giftCertificateService.delete(id);
    }

    @GetMapping("/byTag/{name}")
    public List<GiftCertificateDTO> getCertificatesByTag(@PathVariable(name = "name") String name) throws ServiceException{
        return giftTagService.getCertificatesByTagName(name);
    }

    @GetMapping("/search/{part}")
    public List<GiftCertificateDTO> searchByPartOfCertificateName(@PathVariable(name = "part") String part) throws ServiceException{
        return giftCertificateService.searchByPartOfCertificateName(part);
    }

    @GetMapping("/sort/asc")
    public List<GiftCertificateDTO> sortByASC() throws ServiceException{
        return giftCertificateService.sortByASC();
    }
}

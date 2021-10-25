package com.epam.esm.controller;

import com.epam.esm.bean.GiftCertificateBean;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.ServiceProvider;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/giftCertificate")
public class GiftCertificateController {
    private GiftCertificateService giftCertificateService;
    private ServiceProvider serviceProvider;

    @Autowired
    public GiftCertificateController(){
        serviceProvider = ServiceProvider.getInstance();
        this.giftCertificateService = serviceProvider.getGiftCertificateService();
    }

    @PostMapping
    public void save(@RequestBody GiftCertificateBean giftCertificateBean) throws ServiceException{
        giftCertificateService.save(giftCertificateBean);
    }

    @PostMapping("/update")
    public void updateCertificate(HttpServletRequest request) throws ServiceException{
        HashMap jsonAsMap = RestAssured
                .get(request.getRequestURI())
                .as(HashMap.class);

        giftCertificateService.updateCertificate(jsonAsMap);

    }

    @GetMapping("/all")
    public List<GiftCertificateBean> getAllCertificates() throws ServiceException{
        return giftCertificateService.getAllCertificates();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGiftCertificate(@PathVariable(name = "id") int id) throws ServiceException{
        giftCertificateService.delete(id);
    }
}

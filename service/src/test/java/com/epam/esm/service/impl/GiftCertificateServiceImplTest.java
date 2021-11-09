package com.epam.esm.service.impl;

import com.epam.esm.config.TestConfig;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.service.ServiceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class,
        loader = AnnotationConfigContextLoader.class)
class GiftCertificateServiceImplTest {

    @Autowired
    private GiftCertificateServiceImpl giftCertificateService;

    private static final int id = 12;
    private static final String name = "new";
    private static final String description = "10";
    private static final double price = 10;
    private static final int duration = 10;
    private static final String createDate = "2021-10-10";
    private static final String lastUpdateDate = "2021-10-20";

    private static final GiftCertificateDTO certificate = mock(GiftCertificateDTO.class);

    @BeforeAll
    public static void setUp(){
        when(certificate.getId()).thenReturn(id);
        when(certificate.getName()).thenReturn(name);
        when(certificate.getDescription()).thenReturn(description);
        when(certificate.getPrice()).thenReturn(price);
        when(certificate.getDuration()).thenReturn(duration);
        when(certificate.getCreateDate()).thenReturn(createDate);
        when(certificate.getLastUpdateDate()).thenReturn(lastUpdateDate);
    }

    @Test
    void save() throws ServiceException {
        GiftCertificateDTO newCertificate = new GiftCertificateDTO(name,description, price, duration, createDate, lastUpdateDate);
        when(giftCertificateService.save(newCertificate)).thenReturn(certificate);
    }

    @Test
    void delete() throws ServiceException {
        doNothing().when(giftCertificateService).delete(id);
    }

    @Test
    void getAllCertificates() throws ServiceException {
        List<GiftCertificateDTO> list = new ArrayList<>();

        when(giftCertificateService.getAllCertificates()).thenReturn(list);
    }

    @Test
    void updateCertificate() throws ServiceException {
        //todo
    }

//    @Test
//    void searchByPartOfCertificateName() throws ServiceException {
//        List<GiftCertificateDTO> list = new ArrayList<>();
//
//        when(giftCertificateService.searchByPartOfCertificateName("n")).thenReturn(list);
//    }
//
//    @Test
//    void sort() throws ServiceException {
//        List<GiftCertificateDTO> list = new ArrayList<>();
//
//        when(giftCertificateService.sort("desc")).thenReturn(list);
//    }
//
//    @Test
//    void searchAndSortByPartOfCertificateName() throws ServiceException {
//        List<GiftCertificateDTO> list = new ArrayList<>();
//
//        when(giftCertificateService.searchAndSortByPartOfCertificateName("n", "asc")).thenReturn(list);
//
//    }
}

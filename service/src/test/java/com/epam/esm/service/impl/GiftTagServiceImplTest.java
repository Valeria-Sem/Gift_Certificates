package com.epam.esm.service.impl;

import com.epam.esm.config.TestConfig;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.GiftTagDTO;
import com.epam.esm.service.ServiceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class,
        loader = AnnotationConfigContextLoader.class)
class GiftTagServiceImplTest {

    @Autowired
    private GiftTagServiceImpl giftTagService;

    private static final String tagName = "stars";
    private static final int tagId = 8;
    private static final int id = 19;
    private static final int giftId = 10;
    private static final GiftTagDTO giftTag = mock(GiftTagDTO.class);

    @BeforeAll
    public static void setUp(){
        when(giftTag.getId()).thenReturn(id);
        when(giftTag.getGiftId()).thenReturn(giftId);
        when(giftTag.getTagId()).thenReturn(tagId);

    }

//    @Test
//    void save() throws ServiceException {
//        when(giftTagService.save(giftId, tagName)).thenReturn(giftTag);
//    }

    @Test
    void delete() throws ServiceException {
        doNothing().when(giftTagService).delete(id);
    }

    @Test
    void getCertificatesByTagName() throws ServiceException {
        List<GiftCertificateDTO> list = new ArrayList<>();

        when(giftTagService.getCertificatesByTagName(tagName)).thenReturn(list);
    }

//    @Test
//    void searchAndSortByPartOfCertificateNameAndTag() throws ServiceException {
//        List<GiftCertificateDTO> list = new ArrayList<>();
//
//        when(giftTagService.searchAndSortByPartOfCertificateNameAndTag("tg", tagName, "asc")).thenReturn(list);
//    }
//
//    @Test
//    void searchByPartAndTag() throws ServiceException {
//        List<GiftCertificateDTO> list = new ArrayList<>();
//
//        when(giftTagService.searchByPartAndTag("tg", tagName)).thenReturn(list);
//    }
//
//    @Test
//    void searchAndSortByTag() throws ServiceException {
//        List<GiftCertificateDTO> list = new ArrayList<>();
//
//        when(giftTagService.searchAndSortByTag(tagName, "desc")).thenReturn(list);
//    }
}

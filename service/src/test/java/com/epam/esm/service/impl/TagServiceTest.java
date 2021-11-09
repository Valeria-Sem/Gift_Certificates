package com.epam.esm.service.impl;

import com.epam.esm.config.TestConfig;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dto.TagDTO;
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

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class,
        loader = AnnotationConfigContextLoader.class)
class TagServiceTest {

    @Autowired
    private TagServiceImpl tagServiceImpl;

    private static final String tagName = "massage";
    private static final int id = 10;
    private static final TagDTO tag = mock(TagDTO.class);

    @BeforeAll
    public static void setUp(){
        when(tag.getName()).thenReturn(tagName);
        when(tag.getId()).thenReturn(id);
    }

    @Test
    void save() throws ServiceException {
        TagDTO newTag = new TagDTO(tagName);
        when(tagServiceImpl.save(newTag)).thenReturn(tag);
    }

    @Test
    void delete() throws ServiceException {
        doNothing().when(tagServiceImpl).delete(id);
    }

    @Test
    void getAllTags() throws ServiceException {
        List<TagDTO> list = new ArrayList<>();

        when(tagServiceImpl.getAllTags()).thenReturn(list);
    }

    @Test
    void getTagByName() throws ServiceException {
        when(tagServiceImpl.getTagByName(tagName)).thenReturn(null);
    }
}

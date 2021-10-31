package com.epam.esm.service;

import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.TagEntity;

import java.util.List;

public interface TagService {
    public TagDTO save(TagDTO tag) throws ServiceException;
    public void delete(int id) throws ServiceException;
    public List<TagDTO> getAllTags() throws ServiceException;
    public TagDTO getTagByName(String name) throws ServiceException;
}

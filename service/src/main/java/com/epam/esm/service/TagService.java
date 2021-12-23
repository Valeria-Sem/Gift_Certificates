package com.epam.esm.service;

import com.epam.esm.dto.TagDTO;

import java.util.List;

public interface TagService {
    TagDTO save(TagDTO tag) throws ServiceException;

    void delete(Long id) throws ServiceException;

    List<TagDTO> getAllTags() throws ServiceException;

    TagDTO getTagByName(String name) throws ServiceException;

    List<TagDTO> getTagsByNames(List<TagDTO> tegNames) throws ServiceException;
}

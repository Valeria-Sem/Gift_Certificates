package com.epam.esm.service;

import com.epam.esm.dto.TagDTO;

import java.util.List;
import java.util.Set;

public interface TagService {
    TagDTO save(TagDTO tag);

    void delete(Long id);

    Iterable<TagDTO> getAllTags();

    TagDTO getTagByName(String name);

    List<TagDTO> getTagsByNames(List<TagDTO> tegNames);
}

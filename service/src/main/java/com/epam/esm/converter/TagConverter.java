package com.epam.esm.converter;

import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.TagEntity;

import java.util.List;

public interface TagConverter {
    TagDTO mapToDto(TagEntity tagEntity);

    List<TagDTO> mapToDto(List<TagEntity> tags);

    List<TagEntity> mapToEntity(List<TagDTO> tags);

    TagEntity mapToEntity(TagDTO tagDTO);
}

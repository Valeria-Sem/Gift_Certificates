package com.epam.esm.converter;

import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.entity.TagEntity;

import java.util.List;

public interface TagConverter {
    public TagDTO mapToDto(TagEntity tagEntity);
    public List<TagDTO> mapToDto(List<TagEntity> tags);
    public List<TagEntity> mapToEntity(List<TagDTO> tags);
    public TagEntity mapToEntity(TagDTO tagDTO);
}

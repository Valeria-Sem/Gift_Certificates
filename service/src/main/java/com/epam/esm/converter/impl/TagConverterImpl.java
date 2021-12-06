package com.epam.esm.converter.impl;

import com.epam.esm.converter.TagConverter;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.TagEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagConverterImpl implements TagConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public TagConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TagDTO mapToDto(TagEntity tagEntity) {
        return modelMapper.map(tagEntity, TagDTO.class);
    }

    @Override
    public List<TagDTO> mapToDto(List<TagEntity> tags) {
        return tags.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<TagEntity> mapToEntity(List<TagDTO> tags) {
        return tags.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    @Override
    public TagEntity mapToEntity(TagDTO tagDTO) {
        return modelMapper.map(tagDTO, TagEntity.class);
    }
}

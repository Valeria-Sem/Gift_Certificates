package com.epam.esm.converter.impl;

import com.epam.esm.converter.GiftTagConverter;
import com.epam.esm.dto.GiftTagDTO;
import com.epam.esm.entity.GiftTagEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GiftTagConverterImpl implements GiftTagConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public GiftTagConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GiftTagDTO mapToDto(GiftTagEntity giftTagEntity) {
        return modelMapper.map(giftTagEntity, GiftTagDTO.class);
    }

    @Override
    public List<GiftTagDTO> mapToDto(List<GiftTagEntity> giftTagEntities) {
        return giftTagEntities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<GiftTagEntity> mapToEntity(List<GiftTagDTO> giftTagEntity) {
        return giftTagEntity.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    @Override
    public GiftTagEntity mapToEntity(GiftTagDTO giftTagDTOs) {
        return modelMapper.map(giftTagDTOs, GiftTagEntity.class);
    }
}

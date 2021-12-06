package com.epam.esm.converter;

import com.epam.esm.dto.GiftTagDTO;
import com.epam.esm.entity.GiftTagEntity;

import java.util.List;

public interface GiftTagConverter {
    GiftTagDTO mapToDto(GiftTagEntity giftTagEntity);

    List<GiftTagDTO> mapToDto(List<GiftTagEntity> giftTagEntities);

    List<GiftTagEntity> mapToEntity(List<GiftTagDTO> giftTagDTOs);

    GiftTagEntity mapToEntity(GiftTagDTO giftTagDTOs);
}

package com.epam.esm.converter;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.GiftTagDTO;
import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.entity.GiftTagEntity;

import java.util.List;

public interface GiftTagConverter {
    public GiftTagDTO mapToDto(GiftTagEntity giftTagEntity);
    public List<GiftTagDTO> mapToDto(List<GiftTagEntity> giftTagEntities);
    public List<GiftTagEntity> mapToEntity(List<GiftTagDTO> giftTagDTOs);
    public GiftTagEntity mapToEntity(GiftTagDTO giftTagDTOs);
}

package com.epam.esm.converter;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.entity.GiftCertificateEntity;

import java.util.List;

public interface GiftCertificateConverter {
    GiftCertificateDTO mapToDto(GiftCertificateEntity certificateEntity);

    List<GiftCertificateDTO> mapToDto(List<GiftCertificateEntity> certificates);

    List<GiftCertificateEntity> mapToEntity(List<GiftCertificateDTO> certificates);

    GiftCertificateEntity mapToEntity(GiftCertificateDTO certificateDTO);
}

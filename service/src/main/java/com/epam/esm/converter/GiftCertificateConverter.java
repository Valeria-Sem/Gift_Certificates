package com.epam.esm.converter;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.entity.GiftCertificateEntity;

import java.util.List;

public interface GiftCertificateConverter {
    public GiftCertificateDTO mapToDto(GiftCertificateEntity certificateEntity);
    public List<GiftCertificateDTO> mapToDto(List<GiftCertificateEntity> certificates);
    public List<GiftCertificateEntity> mapToEntity(List<GiftCertificateDTO> certificates);
    public GiftCertificateEntity mapToEntity(GiftCertificateDTO certificateDTO);
}

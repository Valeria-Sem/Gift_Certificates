package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;

import java.util.List;

public interface GiftCertificateService {
    GiftCertificateDTO save(GiftCertificateDTO giftCertificate);

    GiftCertificateDTO getCertificateById(Long id);

    void delete(Long id);

    List<GiftCertificateDTO> getAllCertificates();

    GiftCertificateDTO updateCertificate(GiftCertificateDTO certificate);
}

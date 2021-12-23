package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GiftCertificateService {
    GiftCertificateDTO save(GiftCertificateDTO giftCertificate) throws ServiceException;

    GiftCertificateDTO getCertificateById(Long id) throws ServiceException;

    void delete(Long id) throws ServiceException;

    List<GiftCertificateDTO> getAllCertificates() throws ServiceException;

    GiftCertificateDTO updateCertificate(GiftCertificateDTO certificate) throws ServiceException;

    Set<GiftCertificateDTO> search(Map properties) throws ServiceException;
}

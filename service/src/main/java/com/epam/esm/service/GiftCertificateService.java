package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;

import java.util.HashMap;
import java.util.List;

public interface GiftCertificateService {
    public GiftCertificateDTO save(GiftCertificateDTO giftCertificate) throws ServiceException;
    public void delete(int id) throws ServiceException;
    public List<GiftCertificateDTO> getAllCertificates() throws ServiceException;
    public void updateCertificate(GiftCertificateDTO certificate) throws ServiceException;
}

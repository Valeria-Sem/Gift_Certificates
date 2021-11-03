package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;

import java.util.HashMap;
import java.util.List;

public interface GiftCertificateService {
    public GiftCertificateDTO save(GiftCertificateDTO giftCertificate) throws ServiceException;
    public void delete(int id) throws ServiceException;
    public List<GiftCertificateDTO> getAllCertificates() throws ServiceException;
    public void updateCertificate(HashMap updateParams) throws ServiceException;
    public List<GiftCertificateDTO> searchByPartOfCertificateName(String part) throws ServiceException;
    public List<GiftCertificateDTO> sort(String sort) throws ServiceException;
    public List<GiftCertificateDTO> searchAndSortByPartOfCertificateName(String part, String sort) throws ServiceException;


}

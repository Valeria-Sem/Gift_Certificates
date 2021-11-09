package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;

import java.util.HashMap;
import java.util.List;

public interface GiftTagService {
    public void save(GiftCertificateDTO giftCertificate) throws ServiceException;
    public void delete (int id) throws ServiceException;
    public List<GiftCertificateDTO> getCertificatesByTagName (String name) throws ServiceException;
//    public List<GiftCertificateDTO> searchAndSortByPartOfCertificateNameAndTag(String part, String tag, String sort) throws ServiceException;
//    public List<GiftCertificateDTO> searchByPartAndTag(String part, String tag) throws ServiceException;
//    public List<GiftCertificateDTO> searchAndSortByTag(String tag, String sort) throws ServiceException;
    public List<GiftCertificateDTO> search (HashMap properties) throws ServiceException;
}

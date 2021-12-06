package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;

import java.util.HashMap;
import java.util.List;

public interface GiftTagService {
    void save(int idCertificate, List<TagDTO> tags) throws ServiceException;

    void delete(int id) throws ServiceException;

    List<GiftCertificateDTO> getCertificatesByTagName(String name) throws ServiceException;

    List<GiftCertificateDTO> search(HashMap<String, String> properties) throws ServiceException;

    List<TagDTO> getTagsByCertificateId(int id) throws ServiceException;

}

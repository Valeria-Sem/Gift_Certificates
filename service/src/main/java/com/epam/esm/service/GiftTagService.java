package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;

import java.util.HashMap;
import java.util.List;

public interface GiftTagService {
    public void save(int idCertificate, List<TagDTO> tags) throws ServiceException;
    public void update(int idCertificate, List<String> tags) throws ServiceException;
    public void delete (int id) throws ServiceException;
    public List<GiftCertificateDTO> getCertificatesByTagName (String name) throws ServiceException;
    public List<GiftCertificateDTO> search (HashMap properties) throws ServiceException;
    public List<TagDTO> getTagsByCertificateId (int id) throws ServiceException;

}

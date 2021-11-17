package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GiftTagService {
    public void save(int idCertificate, List<TagDTO> tags) throws ServiceException;
    public void delete (int id) throws ServiceException;
    public List<GiftCertificateDTO> getCertificatesByTagName (String name) throws ServiceException;
    public List<GiftCertificateDTO> search (HashMap<String, String> properties) throws ServiceException;
    public List<TagDTO> getTagsByCertificateId (int id) throws ServiceException;

}

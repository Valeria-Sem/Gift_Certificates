package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.entity.GiftTagEntity;
import com.epam.esm.entity.TagEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GiftTagDAO {
    public GiftTagEntity save(int idCertificate, String tagName) throws DAOException;
    public void delete (int id) throws DAOException;
    public List<GiftCertificateEntity> getCertificatesByTagName (String name) throws DAOException;
    public List<TagEntity> getTagsByCertificateId (int id) throws DAOException;
    public List<GiftCertificateEntity> search (Map<String, String> properties) throws DAOException;
}

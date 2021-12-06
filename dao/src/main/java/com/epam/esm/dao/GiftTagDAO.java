package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.entity.GiftTagEntity;
import com.epam.esm.entity.TagEntity;

import java.util.List;
import java.util.Map;

public interface GiftTagDAO {
    GiftTagEntity save(int idCertificate, String tagName) throws DAOException;

    void delete(int id) throws DAOException;

    List<GiftCertificateEntity> getCertificatesByTagName(String name) throws DAOException;

    List<TagEntity> getTagsByCertificateId(int id) throws DAOException;

    List<GiftCertificateEntity> search(Map<String, String> properties) throws DAOException;
}

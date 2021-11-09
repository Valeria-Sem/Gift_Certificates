package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.entity.GiftTagEntity;

import java.util.HashMap;
import java.util.List;

public interface GiftTagDAO {
    public GiftTagEntity save(int idCertificate, String tagName) throws DAOException;
    public void delete (int id) throws DAOException;
    public List<GiftCertificateEntity> getCertificatesByTagName (String name) throws DAOException;
    public List<String> getTagsByCertificateId (int id) throws DAOException;

    //    public List<GiftCertificateEntity> searchAndSortByPartOfCertificateNameAndTag (String part, String tag, String sort) throws DAOException;
//    public List<GiftCertificateEntity> searchByPartAndTag(String part, String tag) throws DAOException;
//    public List<GiftCertificateEntity> searchAndSortByTag (String tag, String sort) throws DAOException;
    public List<GiftCertificateEntity> search (HashMap properties) throws DAOException;
}

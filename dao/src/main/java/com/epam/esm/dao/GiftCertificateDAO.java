package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificateEntity;

import java.util.HashMap;
import java.util.List;

public interface GiftCertificateDAO {
    public GiftCertificateEntity save (GiftCertificateEntity giftCertificate) throws DAOException;
    public void delete (int id) throws DAOException;
    public List<GiftCertificateEntity> getAllCertificates () throws DAOException;
    public void updateCertificate (HashMap updateParams) throws DAOException;
    public List<GiftCertificateEntity> searchByPartOfCertificateName (String part) throws DAOException;
    public List<GiftCertificateEntity> sortByASC () throws DAOException;
}

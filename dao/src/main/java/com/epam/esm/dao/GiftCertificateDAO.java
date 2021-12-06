package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificateEntity;

import java.util.HashMap;
import java.util.List;

public interface GiftCertificateDAO {
    GiftCertificateEntity save(GiftCertificateEntity giftCertificate) throws DAOException;

    GiftCertificateEntity getCertificateById(int id) throws DAOException;

    void delete(int id) throws DAOException;

    List<GiftCertificateEntity> getAllCertificates() throws DAOException;

    void updateCertificate(GiftCertificateEntity updateParams) throws DAOException;
}

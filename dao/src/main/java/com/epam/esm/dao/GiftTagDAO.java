package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.entity.GiftTagEntity;

import java.util.List;

public interface GiftTagDAO {
    public GiftTagEntity save(GiftTagEntity giftTagEntity) throws DAOException;
    public void delete (int id) throws DAOException;
    public List<GiftCertificateEntity> getCertificatesByTagName (String name) throws DAOException;
}

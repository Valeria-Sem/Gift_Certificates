package com.epam.esm.dao;

import com.epam.esm.bean.GiftCertificateBean;

import java.util.HashMap;
import java.util.List;

public interface GiftTagDAO {
    public void save (int tagId, int giftId) throws DAOException;
    public void delete (int id) throws DAOException;
    public List<GiftCertificateBean> getCertificatesByTagName (String name) throws DAOException;
}

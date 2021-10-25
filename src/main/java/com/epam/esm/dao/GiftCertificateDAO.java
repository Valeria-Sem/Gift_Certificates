package com.epam.esm.dao;

import com.epam.esm.bean.GiftCertificateBean;

import java.util.HashMap;
import java.util.List;

public interface GiftCertificateDAO {
    public void save (GiftCertificateBean giftCertificate) throws DAOException;
    public void delete (int id) throws DAOException;
    public List<GiftCertificateBean> getAllCertificates () throws DAOException;
    public void updateCertificate (HashMap<String, String> updateParams) throws DAOException;
}

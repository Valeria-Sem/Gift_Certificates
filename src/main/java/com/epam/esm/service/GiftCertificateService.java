package com.epam.esm.service;

import com.epam.esm.bean.GiftCertificateBean;
import com.epam.esm.dao.DAOException;

import java.util.HashMap;
import java.util.List;

public interface GiftCertificateService {
    public void save (GiftCertificateBean giftCertificate) throws ServiceException;
    public void delete (int id) throws ServiceException;
    public List<GiftCertificateBean> getAllCertificates () throws ServiceException;
    public void updateCertificate (HashMap<String, String> updateParams) throws ServiceException;

}

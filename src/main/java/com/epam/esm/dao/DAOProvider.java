package com.epam.esm.dao;

import com.epam.esm.dao.impl.GiftCertificateDAOImpl;
import com.epam.esm.dao.impl.GiftTagDAOImpl;
import com.epam.esm.dao.impl.TagDAOImpl;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private final TagDAO tagDAO = new TagDAOImpl();
    private final GiftCertificateDAO giftCertificateDAO = new GiftCertificateDAOImpl();
    private final GiftTagDAO giftTagDAO = new GiftTagDAOImpl();

    public DAOProvider() {
    }

    public static DAOProvider getInstance(){
        return instance;
    }

    public TagDAO getTagDAO() {
        return tagDAO;
    }

    public GiftCertificateDAO getGiftCertificateDAO() {
        return giftCertificateDAO;
    }

    public GiftTagDAO getGiftTagDAO() {
        return giftTagDAO;
    }
}

package com.epam.esm.dao;

import com.epam.esm.dao.impl.TagDAOImpl;

public class DAOProvider {

    private static final DAOProvider instance = new DAOProvider();

    private final TagDAO tagDAO = new TagDAOImpl();

    private DAOProvider(){}

    public static DAOProvider getInstance(){
        return instance;
    }

    public TagDAO getTagDAO() {
        return tagDAO;
    }

}

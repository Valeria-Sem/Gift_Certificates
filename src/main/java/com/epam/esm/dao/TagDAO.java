package com.epam.esm.dao;

public interface TagDAO {

    public void save(String name) throws DAOException;
    public void delete(int id) throws DAOException;

}

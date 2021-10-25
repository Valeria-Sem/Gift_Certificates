package com.epam.esm.dao;

import com.epam.esm.bean.TagBean;

import java.util.List;

public interface TagDAO {

    public void save(String name) throws DAOException;
    public void delete(int id) throws DAOException;
    public List<TagBean> getAllTags() throws DAOException;
}

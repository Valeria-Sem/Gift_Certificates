package com.epam.esm.dao;

import com.epam.esm.entity.TagEntity;

import java.util.List;

public interface TagDAO {

    public TagEntity save(TagEntity tag) throws DAOException;
    public void delete(int id) throws DAOException;
    public List<TagEntity> getAllTags() throws DAOException;
    public TagEntity getTagByName(String name) throws DAOException;
}

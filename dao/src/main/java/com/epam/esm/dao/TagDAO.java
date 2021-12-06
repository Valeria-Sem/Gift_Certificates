package com.epam.esm.dao;

import com.epam.esm.entity.TagEntity;

import java.util.List;

public interface TagDAO {

    TagEntity save(TagEntity tag) throws DAOException;

    void delete(int id) throws DAOException;

    List<TagEntity> getAllTags() throws DAOException;

    TagEntity getTagByName(String name) throws DAOException;
}

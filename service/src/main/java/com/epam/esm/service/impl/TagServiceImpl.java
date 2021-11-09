package com.epam.esm.service.impl;

import com.epam.esm.converter.TagConverter;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.validator.TagValidator;
import com.epam.esm.service.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final Logger LOGGER = Logger.getLogger(TagServiceImpl.class);

    private final TagDAO tagDAO;
    private final TagValidator tagValidator;
    private final TagConverter tagConverter;

    @Autowired
    public TagServiceImpl(TagDAO tagDAO, TagConverter tagConverter, TagValidator tagValidator){
        this.tagDAO = tagDAO;
        this.tagConverter = tagConverter;
        this.tagValidator = tagValidator;
    }

    @Override
    public TagDTO save(TagDTO tag) throws ServiceException {
        try{
            tagValidator.validateTagName(tag.getName());

            return tagConverter.mapToDto(tagDAO.save(tagConverter.mapToEntity(tag)));
        } catch (ValidatorException | DAOException e){
            LOGGER.warn("some service problems with validate or saving tag");
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try{
            tagValidator.validateId(id);

            tagDAO.delete(id);
        } catch (DAOException | ValidatorException e){
            LOGGER.warn("some service problems with deleting tag");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TagDTO> getAllTags() throws ServiceException {
        try{
            return tagConverter.mapToDto(tagDAO.getAllTags());
        } catch (DAOException e){
            LOGGER.warn("some service problems with extracting tags");
            throw new ServiceException(e);
        }
    }

    @Override
    public TagDTO getTagByName(String name) throws ServiceException {
        try {
            tagValidator.validateTagName(name);

            if(tagDAO.getTagByName(name) == null){
                return null;
            } else {
                return tagConverter.mapToDto(tagDAO.getTagByName(name));
            }
        } catch (DAOException | ValidatorException e) {
            LOGGER.warn("some service problems with extracting tag by name");
            throw new ServiceException(e);
        }
    }
}

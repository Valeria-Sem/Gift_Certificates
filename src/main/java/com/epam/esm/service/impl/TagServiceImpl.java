package com.epam.esm.service.impl;

import com.epam.esm.bean.TagBean;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.DAOProvider;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.validator.TagValidator;
import com.epam.esm.service.validator.ValidatorException;
import com.epam.esm.service.validator.Validator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final Logger LOGGER = Logger.getLogger(TagServiceImpl.class);

    private final TagDAO tagDAO;
    private final DAOProvider provider;
    private final TagValidator tagValidator;

    public TagServiceImpl(){
        provider = DAOProvider.getInstance();
        tagDAO = provider.getTagDAO();
        tagValidator = Validator.getInstance().getTagValidator();
    }

    @Override
    public void save(String name) throws ServiceException {
        try{
            tagValidator.validateTagName(name);
            tagDAO.save(name);
        } catch (ValidatorException | DAOException e){
            LOGGER.warn("some service problems with validate or saving tag");
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try{
            tagDAO.delete(id);
        } catch (DAOException e){
            LOGGER.warn("some service problems with deleting tag");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TagBean> getAllTags() throws ServiceException {
        try{
            return tagDAO.getAllTags();
        } catch (DAOException e){
            LOGGER.warn("some service problems with extracting tags");
            throw new ServiceException(e);
        }
    }
}

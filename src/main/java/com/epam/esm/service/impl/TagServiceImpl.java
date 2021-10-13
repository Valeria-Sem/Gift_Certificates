package com.epam.esm.service.impl;

import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.DAOProvider;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.validator.TagValidator;
import com.epam.esm.service.validator.ValidatorException;
import com.epam.esm.service.validator.ValidatorFactory;
import org.apache.log4j.Logger;


public class TagServiceImpl implements TagService {
    private final Logger LOGGER = Logger.getLogger(TagServiceImpl.class);

    private final DAOProvider provider;
    private final TagDAO tagDAO;
    private final TagValidator tagValidator;

    public TagServiceImpl(){
        provider = DAOProvider.getInstance();
        tagDAO = provider.getTagDAO();
        tagValidator = ValidatorFactory.getInstance().getTagValidator();
    }

    @Override
    public void save(String name) throws ServiceException {
        try{
            tagValidator.validateTagName(name);
            tagDAO.save(name);
        } catch (ValidatorException | DAOException e){
            LOGGER.warn("some problems with validate or saving tag");
            throw new ServiceException(e);
        }
    }
}

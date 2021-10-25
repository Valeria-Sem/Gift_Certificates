package com.epam.esm.service.impl;

import com.epam.esm.bean.GiftCertificateBean;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.DAOProvider;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.validator.GiftCertificateValidator;
import com.epam.esm.service.validator.TagValidator;
import com.epam.esm.service.validator.Validator;
import com.epam.esm.service.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final Logger LOGGER = Logger.getLogger(GiftCertificateServiceImpl.class);

    private final GiftCertificateDAO giftCertificateDAO;
    private final DAOProvider provider;
    private final GiftCertificateValidator giftCertificateValidator;

    public GiftCertificateServiceImpl(){
        provider = DAOProvider.getInstance();
        giftCertificateDAO = provider.getGiftCertificateDAO();
        giftCertificateValidator = Validator.getInstance().getGiftCertificateValidator();
    }

    @Override
    public void save(GiftCertificateBean giftCertificate) throws ServiceException {
        try{
            giftCertificateValidator.validateNewCertificate(giftCertificate);
            giftCertificateDAO.save(giftCertificate);
        } catch (ValidatorException | DAOException e){
            LOGGER.warn("some service problems with validate or saving certificate");
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try{
            giftCertificateDAO.delete(id);
        } catch (DAOException e){
            LOGGER.warn("some problems with deleting certificate");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<GiftCertificateBean> getAllCertificates() throws ServiceException {
        try{
            return giftCertificateDAO.getAllCertificates();
        } catch ( DAOException e){
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateCertificate(HashMap<String, String> updateParams) throws ServiceException {
        try{
            giftCertificateDAO.updateCertificate(updateParams);
        } catch ( DAOException e){
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException(e);
        }
    }
}

package com.epam.esm.service.impl;

import com.epam.esm.converter.GiftCertificateConverter;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.validator.GiftCertificateValidator;
import com.epam.esm.service.validator.Validator;
import com.epam.esm.service.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final Logger LOGGER = Logger.getLogger(GiftCertificateServiceImpl.class);

    private final GiftCertificateDAO giftCertificateDAO;
    private final GiftCertificateValidator giftCertificateValidator;
    private final GiftCertificateConverter converter;
    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDAO giftCertificateDAO, GiftCertificateConverter converter){
        this.giftCertificateDAO = giftCertificateDAO;
        this.converter = converter;
        giftCertificateValidator = Validator.getInstance().getGiftCertificateValidator();
    }

    @Override
    public GiftCertificateDTO save(GiftCertificateDTO giftCertificate) throws ServiceException {
        try{
            giftCertificateValidator.validateNewCertificate(converter.mapToEntity(giftCertificate));
            return converter.mapToDto(giftCertificateDAO.save(converter.mapToEntity(giftCertificate)));
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
    public List<GiftCertificateDTO> getAllCertificates() throws ServiceException {
        try{
            return converter.mapToDto(giftCertificateDAO.getAllCertificates());
        } catch ( DAOException e){
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateCertificate(HashMap updateParams) throws ServiceException {
        try{
            giftCertificateDAO.updateCertificate(updateParams);
        } catch ( DAOException e){
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<GiftCertificateDTO> searchByPartOfCertificateName(String part) throws ServiceException {
        try{
            return converter.mapToDto(giftCertificateDAO.searchByPartOfCertificateName(part));
        } catch ( DAOException e){
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<GiftCertificateDTO> sortByASC() throws ServiceException {
        try{
            return converter.mapToDto(giftCertificateDAO.sortByASC());
        } catch ( DAOException e){
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException(e);
        }
    }
}

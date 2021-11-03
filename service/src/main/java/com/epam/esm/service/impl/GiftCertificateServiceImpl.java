package com.epam.esm.service.impl;

import com.epam.esm.converter.GiftCertificateConverter;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.validator.GiftCertificateValidator;
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
    public GiftCertificateServiceImpl(GiftCertificateDAO giftCertificateDAO, GiftCertificateConverter converter,
                                      GiftCertificateValidator giftCertificateValidator){
        this.giftCertificateDAO = giftCertificateDAO;
        this.converter = converter;
        this.giftCertificateValidator = giftCertificateValidator;
    }

    @Override
    public GiftCertificateDTO save(GiftCertificateDTO giftCertificate) throws ServiceException {
        try{
            giftCertificateValidator.validateNewCertificate(giftCertificate);

            return converter.mapToDto(giftCertificateDAO.save(converter.mapToEntity(giftCertificate)));
        } catch (ValidatorException | DAOException e){
            LOGGER.warn("some service problems with validate or saving certificate");
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try{
            giftCertificateValidator.validateId(id);

            giftCertificateDAO.delete(id);
        } catch (ValidatorException | DAOException e) {
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
            giftCertificateValidator.validateUpdateData(updateParams);

            giftCertificateDAO.updateCertificate(updateParams);
        } catch ( DAOException | ValidatorException e){
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException("service.update.certificate.error", e);
        }
    }

    @Override
    public List<GiftCertificateDTO> searchByPartOfCertificateName(String part) throws ServiceException {
        try{
            giftCertificateValidator.validatePartOfName(part);

            return converter.mapToDto(giftCertificateDAO.searchByPartOfCertificateName(part));
        } catch ( DAOException | ValidatorException e) {
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<GiftCertificateDTO> sort(String sort) throws ServiceException {
        try{
            return converter.mapToDto(giftCertificateDAO.sort(sort));
        } catch ( DAOException e){
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<GiftCertificateDTO> searchAndSortByPartOfCertificateName(String part, String sort) throws ServiceException {
        try{
            return converter.mapToDto(giftCertificateDAO.searchAndSortByPartOfCertificateName(part, sort));
        } catch ( DAOException e){
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException(e);
        }
    }
}

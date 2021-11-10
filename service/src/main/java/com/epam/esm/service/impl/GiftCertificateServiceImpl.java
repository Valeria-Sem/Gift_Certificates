package com.epam.esm.service.impl;

import com.epam.esm.converter.GiftCertificateConverter;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.GiftTagService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.validator.GiftCertificateValidator;
import com.epam.esm.service.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final Logger LOGGER = Logger.getLogger(GiftCertificateServiceImpl.class);

    private final GiftCertificateDAO giftCertificateDAO;
    private final GiftCertificateValidator giftCertificateValidator;
    private final GiftCertificateConverter converter;
    private final GiftTagService giftTagService;


    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDAO giftCertificateDAO, GiftCertificateConverter converter,
                                      GiftCertificateValidator giftCertificateValidator, GiftTagService giftTagService){
        this.giftCertificateDAO = giftCertificateDAO;
        this.converter = converter;
        this.giftCertificateValidator = giftCertificateValidator;
        this.giftTagService = giftTagService;
    }

    @Override
    public GiftCertificateDTO save(GiftCertificateDTO giftCertificate) throws ServiceException {
        try{
            giftCertificateValidator.validateNewCertificate(giftCertificate);

            giftCertificate.setCreateDate(LocalDate.now().toString());
            giftCertificate.setLastUpdateDate(LocalDate.now().toString());

            GiftCertificateDTO newCertificate = converter.mapToDto(giftCertificateDAO.save(converter.mapToEntity(giftCertificate)));

            giftTagService.save(newCertificate.getId(), giftCertificate.getTags());

            newCertificate.setTags(giftTagService.getTagsByCertificateId(newCertificate.getId()));
            System.out.println(newCertificate.getTags());

            return newCertificate;
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
            List<GiftCertificateDTO> allCertificates = converter.mapToDto(giftCertificateDAO.getAllCertificates());

            for (int i = 0; i < allCertificates.size(); i++){
                allCertificates.get(i).setTags(giftTagService.getTagsByCertificateId(allCertificates.get(i).getId()));
            }

            return allCertificates;
        } catch ( DAOException e){
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateCertificate(GiftCertificateDTO certificate) throws ServiceException {
        try{
            HashMap updateParams = new HashMap<>();
            updateParams.put("id", certificate.getId());
            updateParams.put("last_update_date", LocalDate.now().toString());
            updateParams.put("name", certificate.getName());
            updateParams.put("description", certificate.getDescription());

            if(certificate.getDuration() != 0.0){
                updateParams.put("duration", certificate.getDuration());
            }

            if(certificate.getPrice() != 0){
                updateParams.put("price", certificate.getPrice());
            }

            giftCertificateValidator.validateUpdateData(updateParams);


            giftTagService.save((Integer) updateParams.get("id"), certificate.getTags());

            giftCertificateDAO.updateCertificate(updateParams);
        } catch (DAOException | ValidatorException e){
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException("service.update.certificate.error", e);
        }
    }
}

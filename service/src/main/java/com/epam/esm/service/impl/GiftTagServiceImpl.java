package com.epam.esm.service.impl;

import com.epam.esm.converter.GiftCertificateConverter;
import com.epam.esm.converter.GiftTagConverter;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.GiftTagDAO;
import com.epam.esm.service.GiftTagService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.validator.TagValidator;
import com.epam.esm.service.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GiftTagServiceImpl implements GiftTagService {
    private final Logger LOGGER = Logger.getLogger(GiftTagServiceImpl.class);

    private final GiftTagDAO giftTagDAO;
    private final GiftCertificateConverter giftCertificateConverter;
    private final GiftTagConverter giftTagConverter;
    private final TagService tagService;
    private final TagValidator tagValidator;

    @Autowired
    public GiftTagServiceImpl(GiftTagDAO giftTagDAO, GiftCertificateConverter giftCertificateConverter,
                              GiftTagConverter giftTagConverter, TagService tagService, TagValidator tagValidator) {
        this.giftTagDAO = giftTagDAO;
        this.giftCertificateConverter = giftCertificateConverter;
        this.giftTagConverter = giftTagConverter;
        this.tagService = tagService;
        this.tagValidator = tagValidator;
    }

    @Override
    public void save(GiftCertificateDTO giftCertificate) throws ServiceException {
        try{
//            tagValidator.validateGiftTag(idCertificate, tagName);

            if(!giftCertificate.getTags().isEmpty()){
                for(String tagName: giftCertificate.getTags()){
                    if(tagService.getTagByName(tagName) == null){
                        TagDTO tagDTO = new TagDTO(tagName);
                        tagService.save(tagDTO);
                    }
                    giftTagConverter.mapToDto(giftTagDAO.save(giftCertificate.getId(), tagName));
                }
            }

        } catch (DAOException e){
            LOGGER.warn("some service problems with validate or saving gift-tag");
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try{
            tagValidator.validateId(id);

            giftTagDAO.delete(id);
        } catch (DAOException | ValidatorException e){
            LOGGER.warn("some service problems with deleting gift-tag");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<GiftCertificateDTO> getCertificatesByTagName(String name) throws ServiceException {
        try{
            tagValidator.validateTagName(name);

            return giftCertificateConverter.mapToDto(giftTagDAO.getCertificatesByTagName(name));
        } catch (ValidatorException | DAOException e){
            LOGGER.warn("some service problems with extracting tags");
            throw new ServiceException(e);
        }
    }

//    @Override
//    public List<GiftCertificateDTO> searchAndSortByPartOfCertificateNameAndTag(String part, String tag, String sort) throws ServiceException {
//        try{
//
//            return giftCertificateConverter.mapToDto(giftTagDAO.searchAndSortByPartOfCertificateNameAndTag(part, tag, sort));
//        } catch (DAOException e){
//            LOGGER.warn("some service problems with extracting tags");
//            throw new ServiceException(e);
//        }
//    }
//
//    @Override
//    public List<GiftCertificateDTO> searchByPartAndTag(String part, String tag) throws ServiceException {
//        try{
//
//            return giftCertificateConverter.mapToDto(giftTagDAO.searchByPartAndTag(part, tag));
//        } catch (DAOException e){
//            LOGGER.warn("some service problems with extracting tags");
//            throw new ServiceException(e);
//        }
//    }
//
//    @Override
//    public List<GiftCertificateDTO> searchAndSortByTag(String tag, String sort) throws ServiceException {
//        try{
//
//            return giftCertificateConverter.mapToDto(giftTagDAO.searchAndSortByTag(tag, sort));
//        } catch (DAOException e){
//            LOGGER.warn("some service problems with extracting tags");
//            throw new ServiceException(e);
//        }
//    }

    @Override
    public List<GiftCertificateDTO> search(HashMap properties) throws ServiceException {
        try{

            return giftCertificateConverter.mapToDto(giftTagDAO.search(properties));
        } catch (DAOException e){
            LOGGER.warn("some service problems with extracting tags");
            throw new ServiceException(e);
        }
    }
}

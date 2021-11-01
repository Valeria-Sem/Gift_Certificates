package com.epam.esm.service.impl;

import com.epam.esm.converter.GiftCertificateConverter;
import com.epam.esm.converter.GiftTagConverter;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.GiftTagDTO;
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
    public GiftTagDTO save(GiftTagDTO giftTagDTO) throws ServiceException {
        try{
            tagValidator.validateGiftTag(giftTagDTO);

            if(tagService.getTagByName(giftTagDTO.getTagName()) == null){
                TagDTO tagDTO = new TagDTO(giftTagDTO.getTagName());
                tagService.save(tagDTO);
            }

           return giftTagConverter.mapToDto(giftTagDAO.save(giftTagConverter.mapToEntity(giftTagDTO)));
        } catch (DAOException | ValidatorException e){
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
}

package com.epam.esm.service.impl;

import com.epam.esm.converter.GiftCertificateConverter;
import com.epam.esm.converter.GiftTagConverter;
import com.epam.esm.converter.TagConverter;
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
    private final TagConverter tagConverter;
    private final TagService tagService;
    private final TagValidator tagValidator;

    @Autowired
    public GiftTagServiceImpl(GiftTagDAO giftTagDAO, GiftCertificateConverter giftCertificateConverter,
                              GiftTagConverter giftTagConverter, TagService tagService, TagValidator tagValidator,
                              TagConverter tagConverter) {
        this.giftTagDAO = giftTagDAO;
        this.giftCertificateConverter = giftCertificateConverter;
        this.giftTagConverter = giftTagConverter;
        this.tagService = tagService;
        this.tagValidator = tagValidator;
        this.tagConverter = tagConverter;
    }

    @Override
    public void save(int idCertificate, List<TagDTO> tags) throws ServiceException {
        try{
            if(!tags.isEmpty()){
                for(TagDTO tag: tags){
                    tagValidator.validateGiftTag(idCertificate, tag.getName());

                    if(tagService.getTagByName(tag.getName()) == null){
                        TagDTO tagDTO = new TagDTO(tag.getName());
                        tagService.save(tagDTO);
                    }
                    giftTagConverter.mapToDto(giftTagDAO.save(idCertificate, tag.getName()));
                }
            }

        } catch (DAOException | ValidatorException e){
            LOGGER.warn("some service problems with validate or saving gift-tag");
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(int idCertificate, List<String> tags) throws ServiceException {
        try{
            if(!tags.isEmpty()){
                for(String tag: tags){
                    tagValidator.validateGiftTag(idCertificate, tag);

                    if(tagService.getTagByName(tag) == null){
                        TagDTO tagDTO = new TagDTO(tag);
                        tagService.save(tagDTO);
                    }
                    giftTagConverter.mapToDto(giftTagDAO.save(idCertificate, tag));
                }
            }

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

            List<GiftCertificateDTO> certificates = giftCertificateConverter.mapToDto(giftTagDAO.getCertificatesByTagName(name));

            for (int i = 0; i < certificates.size(); i++){
                certificates.get(i).setTags(getTagsByCertificateId(certificates.get(i).getId()));
            }

            return certificates;
        } catch (ValidatorException | DAOException e){
            LOGGER.warn("some service problems with extracting tags");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<GiftCertificateDTO> search(HashMap properties) throws ServiceException {
        try{
            List<GiftCertificateDTO> certificates = giftCertificateConverter.mapToDto(giftTagDAO.search(properties));

            for (int i = 0; i < certificates.size(); i++){
                certificates.get(i).setTags(getTagsByCertificateId(certificates.get(i).getId()));
            }

            return certificates;
        } catch (DAOException e){
            LOGGER.warn("some service problems with extracting tags");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TagDTO> getTagsByCertificateId(int id) throws ServiceException {
        try{
            return tagConverter.mapToDto(giftTagDAO.getTagsByCertificateId(id));
        } catch (DAOException e){
            LOGGER.warn("some service problems with extracting tags");
            throw new ServiceException(e);
        }
    }
}

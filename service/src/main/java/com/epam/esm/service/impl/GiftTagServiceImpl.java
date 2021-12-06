package com.epam.esm.service.impl;

import com.epam.esm.converter.GiftCertificateConverter;
import com.epam.esm.converter.GiftTagConverter;
import com.epam.esm.converter.TagConverter;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.GiftTagDAO;
import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.entity.GiftTagEntity;
import com.epam.esm.entity.TagEntity;
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
import java.util.Map;

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
        TagDTO tagDTO;

        try {
            if (!tags.isEmpty()) {

                GiftTagEntity newGiftTagEntity;

                for (TagDTO tag : tags) {
                    tagValidator.validateGiftTag(idCertificate, tag.getName());

                    if (tagService.getTagByName(tag.getName()) == null) {
                        tagDTO = new TagDTO(tag.getName());

                        tagService.save(tagDTO);
                    }

                    newGiftTagEntity = giftTagDAO.save(idCertificate, tag.getName());

                    giftTagConverter.mapToDto(newGiftTagEntity);
                }
            }

        } catch (DAOException e) {
            LOGGER.warn("some service problems with validate or saving gift-tag");
            e.printStackTrace();
            throw new ServiceException(e.getLocalizedMessage(), e);

        } catch (ValidatorException e) {
            LOGGER.warn("some val problems");
            e.printStackTrace();
            throw new ServiceException("Validation failed, please check input data! " + e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            tagValidator.validateId(id);

            giftTagDAO.delete(id);
        } catch (DAOException e) {
            LOGGER.warn("some service problems with deleting gift-tag");
            e.printStackTrace();
            throw new ServiceException(e.getLocalizedMessage(), e);

        } catch (ValidatorException e) {
            LOGGER.warn("some val problems");
            e.printStackTrace();
            throw new ServiceException("Validation failed, please check input data! " + e.getLocalizedMessage(), e);
        }
    }

    @Override
    public List<GiftCertificateDTO> getCertificatesByTagName(String name) throws ServiceException {
        List<GiftCertificateEntity> certificatesEntity;
        List<GiftCertificateDTO> certificatesDTO;
        List<TagDTO> tags;

        try {
            tagValidator.validateTagName(name);

            certificatesEntity = giftTagDAO.getCertificatesByTagName(name);

            if (certificatesEntity.isEmpty()) {
                throw new ServiceException("No certificates by tag name = " + name);
            }

            certificatesDTO = giftCertificateConverter.mapToDto(certificatesEntity);

            for (GiftCertificateDTO certificate : certificatesDTO) {
                tags = getTagsByCertificateId(certificate.getId());

                certificate.setTags(tags);
            }

            return certificatesDTO;

        } catch (DAOException e) {
            LOGGER.warn("some service problems with extracting tags");
            e.printStackTrace();
            throw new ServiceException(e.getLocalizedMessage(), e);

        } catch (ValidatorException e) {
            LOGGER.warn("some val problems");
            e.printStackTrace();
            throw new ServiceException("Validation failed, please check input data! " + e.getLocalizedMessage(), e);
        }
    }

    @Override
    public List<GiftCertificateDTO> search(HashMap<String, String> properties) throws ServiceException {
        List<GiftCertificateEntity> certificatesEntity;
        List<GiftCertificateDTO> certificatesDTO;
        List<TagDTO> tags;

        try {
            certificatesEntity = giftTagDAO.search(properties);

            if (certificatesEntity.isEmpty()) {
                throw new ServiceException("No certificates");
            }

            certificatesDTO = giftCertificateConverter.mapToDto(certificatesEntity);

            for (GiftCertificateDTO certificate : certificatesDTO) {
                tags = getTagsByCertificateId(certificate.getId());

                certificate.setTags(tags);
            }

            return certificatesDTO;

        } catch (DAOException e) {
            LOGGER.warn("some service problems with extracting tags");
            e.printStackTrace();
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public List<TagDTO> getTagsByCertificateId(int id) throws ServiceException {
        List<TagEntity> tagsEntity;

        try {
            tagValidator.validateId(id);

            tagsEntity = giftTagDAO.getTagsByCertificateId(id);

            if (tagsEntity.isEmpty()) {
                throw new ServiceException("No tags by certificate id = " + id);
            }

            return tagConverter.mapToDto(tagsEntity);

        } catch (DAOException e) {
            LOGGER.warn("some service problems with extracting tags");
            e.printStackTrace();
            throw new ServiceException(e.getLocalizedMessage(), e);

        } catch (ValidatorException e) {
            LOGGER.warn("some val problems");
            e.printStackTrace();
            throw new ServiceException("Validation failed, please check input data! " + e.getLocalizedMessage(), e);
        }
    }
}

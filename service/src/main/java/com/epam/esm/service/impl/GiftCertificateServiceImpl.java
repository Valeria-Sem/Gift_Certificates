package com.epam.esm.service.impl;

import com.epam.esm.converter.GiftCertificateConverter;
import com.epam.esm.converter.TagConverter;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.entity.TagEntity;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.RepoException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.validator.GiftCertificateValidator;
import com.epam.esm.service.validator.TagValidator;
import com.epam.esm.service.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateRepository giftRepository;
    private final TagService tagService;
    private final TagConverter tagConverter;
    private final GiftCertificateConverter giftConverter;
    private final GiftCertificateValidator certificateValidator;
    private final TagValidator tagValidator;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateRepository giftRepository,
                                      GiftCertificateConverter giftConverter,
                                      TagService tagService,
                                      TagConverter tagConverter,
                                      GiftCertificateValidator certificateValidator,
                                      TagValidator tagValidator) {
        this.giftRepository = giftRepository;
        this.giftConverter = giftConverter;
        this.tagService = tagService;
        this.tagConverter = tagConverter;
        this.certificateValidator = certificateValidator;
        this.tagValidator = tagValidator;
    }

    @Override
    public GiftCertificateDTO save(GiftCertificateDTO giftCertificate) throws ServiceException {
        GiftCertificateDTO giftDTO;
        GiftCertificateEntity giftEntity;
        List<TagDTO> tags;

        try {
            giftCertificate.setCreateDate(LocalDate.now().toString());
            giftCertificate.setLastUpdateDate(LocalDate.now().toString());

            certificateValidator.validateNewCertificate(giftCertificate);
            tagValidator.validateTagsName(giftCertificate.getTags());

            tags = tagService.getTagsByNames(giftCertificate.getTags());

            giftCertificate.setTags(tags);

            giftEntity = giftConverter.mapToEntity(giftCertificate);
            giftEntity = giftRepository.save(giftEntity);

            giftDTO = giftConverter.mapToDto(giftEntity);

            return giftDTO;
        } catch (Exception e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public GiftCertificateDTO getCertificateById(Long id) throws ServiceException {
        GiftCertificateDTO giftDTO;
        GiftCertificateEntity giftEntity;

        try{
            certificateValidator.validateId(id);

            giftEntity = giftRepository.findGiftCertificateEntityById(id);
            giftDTO = giftConverter.mapToDto(giftEntity);

            return giftDTO;
        } catch (RepoException | ValidatorException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try{
            giftRepository.deleteById(id);
        } catch (Exception e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public List<GiftCertificateDTO> getAllCertificates() throws ServiceException {
        List<GiftCertificateEntity> giftCertificateEntities;
        List<GiftCertificateDTO> giftCertificateDTOs;
        try{
            giftCertificateEntities = giftRepository.findAll();
            giftCertificateDTOs = giftConverter.mapToDto(giftCertificateEntities);

            return giftCertificateDTOs;
        } catch (Exception e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public GiftCertificateDTO updateCertificate(GiftCertificateDTO certificate) throws ServiceException {
        GiftCertificateDTO giftCertificateDTO;
        GiftCertificateEntity giftCertificateEntity;
        Long id;
        double price;

        try {
            certificateValidator.validateId(certificate.getId());
            certificateValidator.validatePrice(certificate.getPrice());

            id = certificate.getId();
            price = certificate.getPrice();

            giftCertificateEntity = giftRepository.findGiftCertificateEntityById(id);
            giftCertificateEntity.setPrice(price);
            giftCertificateEntity = giftRepository.save(giftCertificateEntity);

            giftCertificateDTO = giftConverter.mapToDto(giftCertificateEntity);

            return giftCertificateDTO;
        } catch (RepoException | ValidatorException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }

    }

    @Override
    public Set<GiftCertificateDTO> search(Map properties) throws ServiceException {
        Set<GiftCertificateDTO> giftDTOs;
        Set<GiftCertificateEntity> giftEntities;

        try{
        if(properties.containsKey("tags") && properties.containsKey("part")){
            List<TagDTO> tags = null;
            try {
                tags = tagService.getTagsByNames((List<TagDTO>) properties.get("tags"));
            } catch (com.epam.esm.service.ServiceException e) {
                e.printStackTrace();
            }
            List<TagEntity> tagEntities = tagConverter.mapToEntity(tags);
//
//            Collection<List<TagEntity>> collList = tagEntities.stream().
//                    collect(Collectors.toCollection(() -> new LinkedList<>()));

//            giftEntities = giftRepository.findByGiftTagsInAndNameLikeOrDescriptionLike((Collection<List<TagEntity>>) properties.get("tags"),
//                    (String) properties.get("part"));
        }
        return null;
        } catch (Exception e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }
}

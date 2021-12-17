package com.epam.esm.service.impl;

import com.epam.esm.converter.GiftCertificateConverter;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateRepository giftRepository;
    private final TagService tagService;
    private final GiftCertificateConverter giftConverter;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateRepository giftRepository,
                                      GiftCertificateConverter giftConverter,
                                      TagService tagService) {
        this.giftRepository = giftRepository;
        this.giftConverter = giftConverter;
        this.tagService = tagService;
    }

    @Override
    public GiftCertificateDTO save(GiftCertificateDTO giftCertificate) {
        GiftCertificateDTO giftDTO;
        GiftCertificateEntity giftEntity;
        List<TagDTO> tags;

        giftCertificate.setCreateDate(LocalDate.now().toString());
        giftCertificate.setLastUpdateDate(LocalDate.now().toString());

        tags = tagService.getTagsByNames(giftCertificate.getTags());
        giftCertificate.setTags(tags);

        giftEntity = giftConverter.mapToEntity(giftCertificate);
        giftEntity = giftRepository.save(giftEntity);

        giftDTO = giftConverter.mapToDto(giftEntity);

        return giftDTO;
    }

    @Override
    public GiftCertificateDTO getCertificateById(Long id) {
        GiftCertificateDTO giftDTO;
        GiftCertificateEntity giftEntity;

        giftEntity = giftRepository.findGiftCertificateEntityById(id);
        giftDTO = giftConverter.mapToDto(giftEntity);

        return giftDTO;
    }

    @Override
    public void delete(Long id) {
        giftRepository.deleteById(id);
    }

    @Override
    public List<GiftCertificateDTO> getAllCertificates() {
        List<GiftCertificateEntity> giftCertificateEntities;
        List<GiftCertificateDTO> giftCertificateDTOs;

        giftCertificateEntities = giftRepository.findAll();
        giftCertificateDTOs = giftConverter.mapToDto(giftCertificateEntities);

        return giftCertificateDTOs;
    }

    @Override
    public GiftCertificateDTO updateCertificate(GiftCertificateDTO certificate) {
        GiftCertificateDTO giftCertificateDTO;
        GiftCertificateEntity giftCertificateEntity;
        Long id = certificate.getId();
        double price = certificate.getPrice();

        giftCertificateEntity = giftRepository.findGiftCertificateEntityById(id);
        giftCertificateEntity.setPrice(price);
        giftCertificateEntity = giftRepository.save(giftCertificateEntity);

        giftCertificateDTO = giftConverter.mapToDto(giftCertificateEntity);

        return giftCertificateDTO;

    }
}

package com.epam.esm.converter.impl;

import com.epam.esm.converter.GiftCertificateConverter;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.entity.GiftCertificateEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GiftCertificateConverterImpl implements GiftCertificateConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public GiftCertificateConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GiftCertificateDTO mapToDto(GiftCertificateEntity certificateEntity){
        return modelMapper.map(certificateEntity, GiftCertificateDTO.class);
    }

    public List<GiftCertificateDTO> mapToDto(List<GiftCertificateEntity> certificates){
        return certificates.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<GiftCertificateEntity> mapToEntity(List<GiftCertificateDTO> certificates){
        return certificates.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    public GiftCertificateEntity mapToEntity(GiftCertificateDTO certificateDTO) {
        return modelMapper.map(certificateDTO, GiftCertificateEntity.class);
    }
}

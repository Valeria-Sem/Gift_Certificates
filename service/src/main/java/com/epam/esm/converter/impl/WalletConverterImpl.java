package com.epam.esm.converter.impl;

import com.epam.esm.converter.WalletConverter;
import com.epam.esm.dto.WalletDTO;
import com.epam.esm.entity.WalletEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WalletConverterImpl implements WalletConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public WalletConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public WalletDTO mapToDto(WalletEntity walletEntity) {
        return modelMapper.map(walletEntity, WalletDTO.class);
    }

    @Override
    public List<WalletDTO> mapToDto(List<WalletEntity> wallets) {
        return wallets.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<WalletEntity> mapToEntity(List<WalletDTO> wallets) {
        return wallets.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    @Override
    public WalletEntity mapToEntity(WalletDTO walletDTO) {
        return modelMapper.map(walletDTO, WalletEntity.class);
    }
}
package com.epam.esm.service.impl;

import com.epam.esm.converter.WalletConverter;
import com.epam.esm.dto.WalletDTO;
import com.epam.esm.entity.WalletEntity;
import com.epam.esm.repository.WalletRepository;
import com.epam.esm.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final WalletConverter walletConverter;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, WalletConverter walletConverter) {
        this.walletRepository = walletRepository;
        this.walletConverter = walletConverter;
    }

    @Override
    public WalletDTO save(WalletDTO walletDTO) {
        WalletEntity walletEntity;

        walletEntity = walletConverter.mapToEntity(walletDTO);
        walletEntity = walletRepository.save(walletEntity);

        walletDTO = walletConverter.mapToDto(walletEntity);

        return walletDTO;
    }
}

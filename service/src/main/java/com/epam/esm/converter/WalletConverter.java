package com.epam.esm.converter;

import com.epam.esm.entity.WalletEntity;
import com.epam.esm.dto.WalletDTO;

import java.util.List;

public interface WalletConverter {
    WalletDTO mapToDto(WalletEntity walletEntity);

    List<WalletDTO> mapToDto(List<WalletEntity> wallets);

    List<WalletEntity> mapToEntity(List<WalletDTO> wallets);

    WalletEntity mapToEntity(WalletDTO walletDTO);
}
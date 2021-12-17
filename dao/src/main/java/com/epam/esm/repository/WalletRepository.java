package com.epam.esm.repository;

import com.epam.esm.entity.UserEntity;
import com.epam.esm.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
//    WalletEntity findWalletEntityByUser(UserEntity user);
}

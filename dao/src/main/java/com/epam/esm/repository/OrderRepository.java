package com.epam.esm.repository;

import com.epam.esm.entity.OrderEntity;
import com.epam.esm.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findOrderEntityById(Long id);
    List<OrderEntity> findOrderEntitiesByUser(UserEntity user);
}

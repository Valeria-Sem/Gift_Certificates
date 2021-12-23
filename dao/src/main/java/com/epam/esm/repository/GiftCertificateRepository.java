package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Repository
public interface GiftCertificateRepository extends JpaRepository<GiftCertificateEntity, Long> {
    GiftCertificateEntity findGiftCertificateEntityById(Long id) throws RepoException;

    Set<GiftCertificateEntity> findByGiftTagsInAndNameLikeOrDescriptionLike(Collection<List<TagEntity>> giftTags,
                                                                            String name, String description) throws RepoException;

    Set<GiftCertificateEntity> findByGiftTagsIn(Collection<List<TagEntity>> giftTags) throws RepoException;

    Set<GiftCertificateEntity> findByNameLikeOrDescriptionLike(String name, String description) throws RepoException;
}
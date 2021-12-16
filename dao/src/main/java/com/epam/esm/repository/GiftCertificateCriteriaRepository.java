package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.model.GiftCertificatePage;
import com.epam.esm.model.GiftCertificateSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class GiftCertificateCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public GiftCertificateCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<GiftCertificateEntity> findAllWithFilters(GiftCertificatePage page,
                                                          GiftCertificateSearchCriteria criteria){
        CriteriaQuery<GiftCertificateEntity> criteriaQuery = criteriaBuilder.createQuery(GiftCertificateEntity.class);
        Root<GiftCertificateEntity> giftRoot = criteriaQuery.from(GiftCertificateEntity.class);
        Predicate predicate = getPredicate(criteria, giftRoot);
        criteriaQuery.where(predicate);
        setOrder(page, criteriaQuery, giftRoot);
        TypedQuery<GiftCertificateEntity> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(page.getPageNumber() * page.getPageSize());
        typedQuery.setMaxResults(page.getPageSize());

        Pageable pageable = getPageable(page);

        long giftCount = getGiftCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, giftCount);
    }



    private Predicate getPredicate(GiftCertificateSearchCriteria criteria,
                                   Root<GiftCertificateEntity> giftRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(criteria.getName())){
            predicates.add(
                    criteriaBuilder.like(giftRoot.get("name"),
                            "%" + criteria.getName() + "%")
            );
        }

        if(Objects.nonNull(criteria.getDescription())){
            predicates.add(
                    criteriaBuilder.like(giftRoot.get("description"),
                            "%" + criteria.getDescription() + "%")
            );
        }

        if(Objects.nonNull(criteria.getTag())){
            predicates.add(
                    criteriaBuilder.like(giftRoot.get("name"),
                            "%" + criteria.getTag() + "%")
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(GiftCertificatePage page,
                          CriteriaQuery<GiftCertificateEntity> criteriaQuery,
                          Root<GiftCertificateEntity> giftRoot) {
        if(page.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(giftRoot.get(page.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(giftRoot.get(page.getSortBy())));

        }
    }

    private Pageable getPageable(GiftCertificatePage page) {
        Sort sort = Sort.by(page.getSortDirection(), page.getSortBy());

        return PageRequest.of(
                page.getPageNumber(),
                page.getPageSize(),
                sort
        );
    }

    private long getGiftCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<GiftCertificateEntity> countRoot = countQuery.from(GiftCertificateEntity.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
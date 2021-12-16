package com.epam.esm.repository;

import com.epam.esm.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface TagRepository extends CrudRepository<TagEntity, Long> {
    TagEntity findTagEntityByName(String name);
}
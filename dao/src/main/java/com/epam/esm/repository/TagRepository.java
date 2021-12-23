package com.epam.esm.repository;

import com.epam.esm.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
    TagEntity findTagEntityByName(String name) throws RepoException;
}
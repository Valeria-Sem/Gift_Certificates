package com.epam.esm.service.impl;

import com.epam.esm.converter.TagConverter;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.TagEntity;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagConverter tagConverter;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagConverter tagConverter) {
        this.tagRepository = tagRepository;
        this.tagConverter = tagConverter;
    }

    @Override
    public TagDTO save(TagDTO tag) {
        TagEntity tagEntity;
        TagDTO tagDTO;

        try {
            tagEntity = tagConverter.mapToEntity(tag);
            tagEntity = tagRepository.save(tagEntity);

            tagDTO = tagConverter.mapToDto(tagEntity);

            return tagDTO;
        } catch (Exception ex){
            ex.printStackTrace();
            ex.getLocalizedMessage();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);

    }

    @Override
    public Iterable<TagDTO> getAllTags() {
        List<TagEntity> tagEntities;
        List<TagDTO> tagDTOs;

        tagEntities = tagRepository.findAll();
        tagDTOs = tagConverter.mapToDto(tagEntities);

        return tagDTOs;
    }

    @Override
    public TagDTO getTagByName(String name) {
        TagEntity tagEntity;
        TagDTO tagDTO;

        tagEntity = tagRepository.findTagEntityByName(name);
        tagDTO = tagConverter.mapToDto(tagEntity);

        return tagDTO;
    }

    @Override
    public List<TagDTO> getTagsByNames(List<TagDTO> tegNames) {
        List<TagDTO> tagDTOs;
        List<TagEntity> tagEntities = new ArrayList<>();
        TagDTO newTagDTO;
        TagEntity newTagEntity;

        for(TagDTO tag: tegNames) {
            if (tagRepository.findTagEntityByName(tag.getName()) != null){
                tagEntities.add(tagRepository.findTagEntityByName(tag.getName()));
            } else {
                newTagDTO = save(tag);
                newTagEntity = tagConverter.mapToEntity(newTagDTO);

                tagEntities.add(newTagEntity);
            }
        }

        tagDTOs = tagConverter.mapToDto(tagEntities);

        return tagDTOs;
    }
}

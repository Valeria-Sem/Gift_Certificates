package com.epam.esm.service.impl;

import com.epam.esm.converter.TagConverter;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.TagEntity;
import com.epam.esm.repository.RepoException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.validator.TagValidator;
import com.epam.esm.service.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagConverter tagConverter;
    private final TagValidator tagValidator;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository,
                          TagConverter tagConverter,
                          TagValidator tagValidator) {
        this.tagRepository = tagRepository;
        this.tagConverter = tagConverter;
        this.tagValidator = tagValidator;
    }

    @Override
    public TagDTO save(TagDTO tag) throws ServiceException {
        TagEntity tagEntity;
        TagDTO tagDTO;

        try {
            tagValidator.validateTagName(tag.getName());

            tagEntity = tagConverter.mapToEntity(tag);
            tagEntity = tagRepository.save(tagEntity);

            tagDTO = tagConverter.mapToDto(tagEntity);

            return tagDTO;
        } catch (Exception e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException{
        try{
            tagValidator.validateId(id);
            tagRepository.deleteById(id);
        } catch (Exception e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public List<TagDTO> getAllTags() throws ServiceException{
        List<TagEntity> tagEntities;
        List<TagDTO> tagDTOs;

        try{
            tagEntities = tagRepository.findAll();
            tagDTOs = tagConverter.mapToDto(tagEntities);

            return tagDTOs;
        } catch (Exception e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public TagDTO getTagByName(String name) throws ServiceException{
        TagEntity tagEntity;
        TagDTO tagDTO;

        try{
            tagValidator.validateTagName(name);

            tagEntity = tagRepository.findTagEntityByName(name);
            tagDTO = tagConverter.mapToDto(tagEntity);

            return tagDTO;
        } catch (RepoException | ValidatorException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public List<TagDTO> getTagsByNames(List<TagDTO> tegNames) throws ServiceException {
        List<TagDTO> tagDTOs;
        List<TagEntity> tagEntities = new ArrayList<>();
        TagDTO newTagDTO;
        TagEntity newTagEntity;

        try{
            tagValidator.validateTagsName(tegNames);

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
        } catch (RepoException | ValidatorException e){
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }
}

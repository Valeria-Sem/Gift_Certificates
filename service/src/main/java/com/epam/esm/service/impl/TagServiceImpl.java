package com.epam.esm.service.impl;

//import com.epam.esm.converter.TagConverter;
//import com.epam.esm.dto.TagDTO;
//import com.epam.esm.entity.TagEntity;
//import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
//import com.epam.esm.service.validator.TagValidator;
//import com.epam.esm.service.validator.ValidatorException;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.List;

@Service
public class TagServiceImpl implements TagService {
//    private final Logger LOGGER = Logger.getLogger(TagServiceImpl.class);
//
//    private final TagDAO tagDAO;
//    private final TagValidator tagValidator;
//    private final TagConverter tagConverter;
//
//    @Autowired
//    public TagServiceImpl(TagDAO tagDAO, TagConverter tagConverter, TagValidator tagValidator) {
//        this.tagDAO = tagDAO;
//        this.tagConverter = tagConverter;
//        this.tagValidator = tagValidator;
//    }
//
//    @Override
//    public TagDTO save(TagDTO tag) throws ServiceException {
//        TagDTO tagDTO;
//        TagEntity tagEntity;
//        try {
//            tagValidator.validateTagName(tag.getName());
//
//            tagEntity = tagConverter.mapToEntity(tag);
//            tagEntity = tagDAO.save(tagEntity);
//
//            tagDTO = tagConverter.mapToDto(tagEntity);
//
//            return tagDTO;
//
//        } catch (DAOException e) {
//            LOGGER.warn("some service problems with validate or saving tag");
//            e.printStackTrace();
//            throw new ServiceException(e.getLocalizedMessage(), e);
//
//        } catch (ValidatorException e) {
//            LOGGER.warn("some val problems");
//            e.printStackTrace();
//            throw new ServiceException("Validation failed, please check input data! " + e.getLocalizedMessage(), e);
//        }
//    }
//
//    @Override
//    public void delete(int id) throws ServiceException {
//        try {
//            tagValidator.validateId(id);
//
//            tagDAO.delete(id);
//
//        } catch (DAOException e) {
//            LOGGER.warn("some service problems with deleting tag");
//            e.printStackTrace();
//            throw new ServiceException(e.getLocalizedMessage(), e);
//
//        } catch (ValidatorException e) {
//            LOGGER.warn("some val problems");
//            e.printStackTrace();
//            throw new ServiceException("Validation failed, please check input data! " + e.getLocalizedMessage(), e);
//        }
//    }
//
//    @Override
//    public List<TagDTO> getAllTags() throws ServiceException {
//        List<TagDTO> tagDTOS;
//        List<TagEntity> tagEntities;
//
//        try {
//            tagEntities = tagDAO.getAllTags();
//
//            if (tagEntities.isEmpty()) {
//                throw new ServiceException("No tags");
//            }
//
//            tagDTOS = tagConverter.mapToDto(tagEntities);
//
//            return tagDTOS;
//        } catch (DAOException e) {
//            LOGGER.warn("some service problems with extracting tags");
//            e.printStackTrace();
//            throw new ServiceException(e.getLocalizedMessage(), e);
//        }
//    }
//
//    @Override
//    public TagDTO getTagByName(String name) throws ServiceException {
//        TagEntity tagEntity;
//        TagDTO tagDTO;
//
//        try {
//            tagValidator.validateTagName(name);
//
//            tagEntity = tagDAO.getTagByName(name);
//
//            tagDTO = tagConverter.mapToDto(tagEntity);
//
//            return tagDTO;
//
//        } catch (DAOException e) {
//            LOGGER.warn("some service problems with extracting tag by name");
//            e.printStackTrace();
//            throw new ServiceException(e.getLocalizedMessage(), e);
//
//        } catch (ValidatorException e) {
//            LOGGER.warn("some val problems");
//            e.printStackTrace();
//            throw new ServiceException("Validation failed, please check input data! " + e.getLocalizedMessage(), e);
//        }
//    }
}

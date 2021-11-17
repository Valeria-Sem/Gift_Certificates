package com.epam.esm.service.impl;

import com.epam.esm.converter.GiftCertificateConverter;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.GiftTagService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.validator.GiftCertificateValidator;
import com.epam.esm.service.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.diffplug.durian.FieldsAndGetters;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final Logger LOGGER = Logger.getLogger(GiftCertificateServiceImpl.class);

    private final GiftCertificateDAO giftCertificateDAO;
    private final GiftCertificateValidator giftCertificateValidator;
    private final GiftCertificateConverter converter;
    private final GiftTagService giftTagService;


    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDAO giftCertificateDAO, GiftCertificateConverter converter,
                                      GiftCertificateValidator giftCertificateValidator, GiftTagService giftTagService){
        this.giftCertificateDAO = giftCertificateDAO;
        this.converter = converter;
        this.giftCertificateValidator = giftCertificateValidator;
        this.giftTagService = giftTagService;
    }

    @Override
    public GiftCertificateDTO save(GiftCertificateDTO giftCertificate) throws ServiceException {
        try{
            giftCertificateValidator.validateNewCertificate(giftCertificate);

            giftCertificate.setCreateDate(LocalDate.now().toString());
            giftCertificate.setLastUpdateDate(LocalDate.now().toString());

            GiftCertificateEntity newEntity = converter.mapToEntity(giftCertificate);
            newEntity = giftCertificateDAO.save(newEntity);

            GiftCertificateDTO newCertificate = converter.mapToDto(newEntity);

            giftTagService.save(newCertificate.getId(), giftCertificate.getTags());

            List<TagDTO> tags = giftTagService.getTagsByCertificateId(newCertificate.getId());

            newCertificate.setTags(tags);

            return newCertificate;

        } catch (ValidatorException | DAOException e){
            LOGGER.warn("some service problems with validate or saving certificate");
            throw new ServiceException(e);
        }
    }

    @Override
    public GiftCertificateDTO getCertificateById(int id) throws ServiceException {
        try{
            giftCertificateValidator.validateId(id);

            GiftCertificateEntity newCertificateEntity = giftCertificateDAO.getCertificateById(id);

            return converter.mapToDto(newCertificateEntity);

        } catch (DAOException e){
            LOGGER.warn("some service problems");
            e.printStackTrace();

            throw new ServiceException(e);
        } catch (ValidatorException e) {
            LOGGER.warn("some val problems");
            e.printStackTrace();
            throw new ServiceException(e);

        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try{
            giftCertificateValidator.validateId(id);

            giftCertificateDAO.delete(id);
        } catch (ValidatorException | DAOException e) {
            LOGGER.warn("some problems with deleting certificate");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<GiftCertificateDTO> getAllCertificates() throws ServiceException {
        try{
            List<GiftCertificateEntity> allCertificatesEntity = giftCertificateDAO.getAllCertificates();

            List<GiftCertificateDTO> allCertificatesDTO = converter.mapToDto(allCertificatesEntity);

            for (GiftCertificateDTO certificate : allCertificatesDTO) {
                List<TagDTO> tags = giftTagService.getTagsByCertificateId(certificate.getId());

                certificate.setTags(tags);
            }

            return allCertificatesDTO;

        } catch ( DAOException e){
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateCertificate(GiftCertificateDTO certificate) throws ServiceException {
        try{

            certificate.setLastUpdateDate(LocalDate.now().toString());

            GiftCertificateDTO oldCertificate = getCertificateById(certificate.getId());

            BeanUtils.copyProperties(oldCertificate, certificate);

            System.out.println(certificate);
//            if(certificate.getName() == null){
//                certificate.setName(oldCertificate.getName());
//            }
//
//            if(certificate.getDuration() == 0){
//                certificate.setDuration(oldCertificate.getDuration());
//            }
//
//            if(certificate.getDescription() == null){
//                certificate.setDescription(oldCertificate.getDescription());
//            }
//
//            if(certificate.getPrice() == 0){
//                certificate.setPrice(oldCertificate.getPrice());
//            }


            GiftCertificateEntity updatedCertificate = converter.mapToEntity(certificate);
            giftCertificateDAO.updateCertificate(updatedCertificate);

        } catch (DAOException e){
            LOGGER.warn("some service problems with extracting certificates");
            throw new ServiceException("service.update.certificate.error", e);
        }

//            Field[] fields = certificate.getClass().getDeclaredFields();
//            Field[] oldFields = oldCertificate.getClass().getDeclaredFields();

//        for(Field f : fields) {
//
//            try {
//                Class t = f.getType();
//                Object v =  f.get(certificate);
//
//                if (t == boolean.class && Boolean.FALSE.equals(v)) {
//                    System.out.println(v);
//
//                } else if (t.isPrimitive() && ((Number) v).doubleValue() == 0) {
//                    System.out.println(v);
//
//                } else if (!t.isPrimitive() && v == null) {
//                    System.out.println(v);
//
//                }
//
//                System.out.println(v);
//
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//        }

//        .map(Field::getName)
//        FieldsAndGetters f;
//        Arrays.stream(fields)
//                    .forEach(field -> {
//                        try {
//                            Class<?> t = field.getType();
//                            Object v = field.get(certificate);
//
//                            if (t == boolean.class && Boolean.FALSE.equals(v)) {
//                                System.out.println(v);
//                            } else if (t.isPrimitive() && ((Number) v).doubleValue() == 0) {
//                                System.out.println(v);
//
//                            } else if (!t.isPrimitive() && v == null) {
//                                System.out.println(v);
//
//                            }
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    });
    }



}

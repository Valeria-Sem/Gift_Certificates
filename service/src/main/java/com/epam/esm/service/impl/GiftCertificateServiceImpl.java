package com.epam.esm.service.impl;

//import com.epam.esm.converter.GiftCertificateConverter;
//import com.epam.esm.dto.GiftCertificateDTO;
//import com.epam.esm.dto.TagDTO;
//import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.service.GiftCertificateService;
//import com.epam.esm.service.ServiceException;
//import com.epam.esm.service.validator.GiftCertificateValidator;
//import com.epam.esm.service.validator.ValidatorException;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.time.LocalDate;
//import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
//    private final Logger LOGGER = Logger.getLogger(GiftCertificateServiceImpl.class);
//
//    private final GiftCertificateDAO giftCertificateDAO;
//    private final GiftCertificateValidator giftCertificateValidator;
//    private final GiftCertificateConverter converter;
//    private final GiftTagService giftTagService;
//
//
//    @Autowired
//    public GiftCertificateServiceImpl(GiftCertificateDAO giftCertificateDAO, GiftCertificateConverter converter,
//                                      GiftCertificateValidator giftCertificateValidator, GiftTagService giftTagService) {
//        this.giftCertificateDAO = giftCertificateDAO;
//        this.converter = converter;
//        this.giftCertificateValidator = giftCertificateValidator;
//        this.giftTagService = giftTagService;
//    }
//
//    @Override
//    public GiftCertificateDTO save(GiftCertificateDTO giftCertificate) throws ServiceException {
//        GiftCertificateEntity newEntity;
//        GiftCertificateDTO newCertificate;
//        List<TagDTO> tags;
//
//        try {
//            giftCertificateValidator.validateNewCertificate(giftCertificate);
//
//            giftCertificate.setCreateDate(LocalDate.now().toString());
//            giftCertificate.setLastUpdateDate(LocalDate.now().toString());
//
//            newEntity = converter.mapToEntity(giftCertificate);
//            newEntity = giftCertificateDAO.save(newEntity);
//
//            newCertificate = converter.mapToDto(newEntity);
//
//            giftTagService.save(newCertificate.getId(), giftCertificate.getTags());
//
//            tags = giftTagService.getTagsByCertificateId(newCertificate.getId());
//
//            newCertificate.setTags(tags);
//
//            return newCertificate;
//
//        } catch (DAOException e) {
//            LOGGER.warn("some service problems with saving certificate");
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
//    public GiftCertificateDTO getCertificateById(int id) throws ServiceException {
//        GiftCertificateEntity certificateEntity;
//        GiftCertificateDTO certificateDTO;
//        List<TagDTO> tags;
//
//        try {
//            giftCertificateValidator.validateId(id);
//
//            certificateEntity = giftCertificateDAO.getCertificateById(id);
//
//            certificateDTO = converter.mapToDto(certificateEntity);
//
//            tags = giftTagService.getTagsByCertificateId(id);
//
//            certificateDTO.setTags(tags);
//
//            return certificateDTO;
//
//        } catch (DAOException e) {
//            LOGGER.warn("some service problems");
//            e.printStackTrace();
//            throw new ServiceException(e.getLocalizedMessage(), e);
//
//        } catch (ValidatorException e) {
//            LOGGER.warn("some val problems");
//            e.printStackTrace();
//            throw new ServiceException("Validation failed, please check input data! " + e.getLocalizedMessage(), e);
//
//        }
//    }
//
//    @Override
//    public void delete(int id) throws ServiceException {
//        try {
//            giftCertificateValidator.validateId(id);
//
//            giftCertificateDAO.delete(id);
//
//        } catch (DAOException e) {
//            LOGGER.warn("some problems with deleting certificate");
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
//    public List<GiftCertificateDTO> getAllCertificates() throws ServiceException {
//        List<GiftCertificateEntity> allCertificatesEntity;
//        List<GiftCertificateDTO> allCertificatesDTO;
//        List<TagDTO> tags;
//
//        try {
//            allCertificatesEntity = giftCertificateDAO.getAllCertificates();
//
//            allCertificatesDTO = converter.mapToDto(allCertificatesEntity);
//
//            if (allCertificatesDTO.isEmpty()) {
//                throw new ServiceException("No certificates in Database");
//            }
//
//            for (GiftCertificateDTO certificate : allCertificatesDTO) {
//                tags = giftTagService.getTagsByCertificateId(certificate.getId());
//
//                certificate.setTags(tags);
//            }
//
//            return allCertificatesDTO;
//
//        } catch (DAOException e) {
//            LOGGER.warn("some service problems with extracting certificates");
//            e.printStackTrace();
//            throw new ServiceException(e.getLocalizedMessage(), e);
//        }
//    }
//
//    @Override
//    public void updateCertificate(GiftCertificateDTO certificate) throws ServiceException {
//        GiftCertificateDTO oldCertificate;
//
//        try {
//            certificate.setLastUpdateDate(LocalDate.now().toString());
//
//            oldCertificate = getCertificateById(certificate.getId());
//
//            if (certificate.getName() == null) {
//                certificate.setName(oldCertificate.getName());
//            }
//
//            if (certificate.getDuration() == 0) {
//                certificate.setDuration(oldCertificate.getDuration());
//            }
//
//            if (certificate.getDescription() == null) {
//                certificate.setDescription(oldCertificate.getDescription());
//            }
//
//            if (certificate.getPrice() == 0) {
//                certificate.setPrice(oldCertificate.getPrice());
//            }
//
//            giftCertificateValidator.validateNewCertificate(certificate);
//
//            if (certificate.getTags() != null) {
//                giftTagService.save(certificate.getId(), certificate.getTags());
//            }
//
//
//            GiftCertificateEntity updatedCertificate = converter.mapToEntity(certificate);
//            giftCertificateDAO.updateCertificate(updatedCertificate);
//
//        } catch (DAOException e) {
//            LOGGER.warn("some service problems with update Certificate");
//            throw new ServiceException(e.getLocalizedMessage(), e);
//
//        } catch (ValidatorException e) {
//            e.printStackTrace();
//            throw new ServiceException("Validation failed, please check input data! " + e.getLocalizedMessage(), e);
//
//        }
//    }
}

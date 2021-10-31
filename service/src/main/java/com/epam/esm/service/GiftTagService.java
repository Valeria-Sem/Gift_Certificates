package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.GiftTagDTO;

import java.util.List;

public interface GiftTagService {
    public GiftTagDTO save (GiftTagDTO giftTagDTO) throws ServiceException;
    public void delete (int id) throws ServiceException;
    public List<GiftCertificateDTO> getCertificatesByTagName (String name) throws ServiceException;
}

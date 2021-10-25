package com.epam.esm.service;

import com.epam.esm.bean.GiftCertificateBean;
import com.epam.esm.bean.TagBean;

import java.util.List;

public interface TagService {
    public void save(String name) throws ServiceException;
    public void delete(int id) throws ServiceException;
    public List<TagBean> getAllTags() throws ServiceException;
}

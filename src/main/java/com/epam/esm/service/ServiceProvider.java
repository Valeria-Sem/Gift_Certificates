package com.epam.esm.service;

import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import com.epam.esm.service.impl.TagServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private final TagService tagService = new TagServiceImpl();
    private final GiftCertificateService giftCertificateService = new GiftCertificateServiceImpl();

    public ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public TagService getTagService() {
        return tagService;
    }

    public GiftCertificateService getGiftCertificateService() {
        return giftCertificateService;
    }
}

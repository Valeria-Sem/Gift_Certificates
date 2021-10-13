package com.epam.esm.service;

import com.epam.esm.service.impl.TagServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private final TagService tagService = new TagServiceImpl();

    public ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public TagService getTagService() {
        return tagService;
    }
}

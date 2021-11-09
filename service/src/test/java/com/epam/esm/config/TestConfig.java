package com.epam.esm.config;

import com.epam.esm.converter.TagConverter;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.service.GiftTagService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import com.epam.esm.service.impl.GiftTagServiceImpl;
import com.epam.esm.service.impl.TagServiceImpl;
import com.epam.esm.service.validator.TagValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    public TagServiceImpl tagServiceImpl() {
        return mock(TagServiceImpl.class);
    }

    @Bean
    public GiftTagServiceImpl giftTagServiceImpl() {
        return mock(GiftTagServiceImpl.class);
    }

    @Bean
    public GiftCertificateServiceImpl giftCertificateServiceImpl() {
        return mock(GiftCertificateServiceImpl.class);
    }
}

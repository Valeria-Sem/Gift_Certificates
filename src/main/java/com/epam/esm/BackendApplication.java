package com.epam.esm;

import com.epam.esm.bean.TagBean;
import com.epam.esm.config.SpringConfig;
import com.epam.esm.controller.TagController;
import com.epam.esm.dao.DAOException;
import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.ServiceProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BackendApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );

        TagBean tagBean = context.getBean("tagBean", TagBean.class);
        System.out.println(tagBean.getName());

        TagDAOImpl tagDAO = new TagDAOImpl();
        TagController tagController = new TagController();

        try {
            tagController.save("fff");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
//
        context.close();
    }
}

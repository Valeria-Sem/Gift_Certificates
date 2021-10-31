package com.epam.esm;

import com.epam.esm.config.SpringConfig;
import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.controller.TagController;
import com.epam.esm.service.ServiceException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//import javax.servlet.http.HttpServletRequest;


public class BackendApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );


////        TagDAOImpl tagDAO = new TagDAOImpl();
//        TagController tagController = new TagController();
//        GiftCertificateController giftCertificateController = new GiftCertificateController();
//
//        try {
//           // tagController.save("ffef");
//            System.out.println(giftCertificateController.getAllCertificates());
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }

        context.close();
    }
}

package com.epam.esm;

import com.epam.esm.bean.TagBean;
import com.epam.esm.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BackendApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );

        TagBean tagBean = context.getBean("tagBean", TagBean.class);
        System.out.println(tagBean.getName());
//
        context.close();
    }
}

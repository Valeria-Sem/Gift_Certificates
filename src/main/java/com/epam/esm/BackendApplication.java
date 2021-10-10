package com.epam.esm;

import com.epam.esm.bean.TagBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BackendApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        TagBean tagBean = context.getBean("tagBean", TagBean.class);

        context.close();
    }
}

package com.gez.woodware.util;


import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

/**
 * 工具类获取配置文件中的值
 */
public class ResourcesApplication {


    /**
     * Created by admin on 2019/1/4.
     */

    private static String PROPERTY_NAME = "application.yml";

    public static Object getCommonYml(Object key) {


        Resource resource = new ClassPathResource(PROPERTY_NAME);
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();

            System.out.println(yamlFactory.toString());
            yamlFactory.setResources(resource);
            properties = yamlFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return properties.get(key);
    }

    public static void main(String[] args) {
        System.out.println(getCommonYml("resources.path"));
    }


}

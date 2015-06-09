package com.huivip.steel.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by sunlaihui on 6/9/15.
 */
public class SteelConfig {
    public static String getConfigureAttachDir(){
        Properties prop = new Properties();
        try {
            prop.load(new ClassPathResource("steel.properties").getInputStream());
        } catch (IOException e) {

        }
        // the directory to upload to
        String configureUploadDir=prop.getProperty("editorAttachDirectroy");
        return configureUploadDir;
    }
}

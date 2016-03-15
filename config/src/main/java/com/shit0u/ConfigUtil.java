package com.shit0u;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shit0u
 * Date: 16/3/15
 * Time: 15:41
 */

import com.google.common.base.Strings;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class ConfigUtil {
    public static final String EXTERNAL_CONFIG_FILE_PATH = "sys_external_config";
    public static final Logger log = Logger.getLogger(ConfigUtil.class);

    public static Configuration configuration;

    static {
        configuration = loadConfiguration();
    }

    private static Configuration loadConfiguration() {
        try {
            CompositeConfiguration compositeConfiguration = new CompositeConfiguration();

            //Step 1. load external config file, if file exist, will override the system default config file
            String externalConfigPath = System.getProperty(EXTERNAL_CONFIG_FILE_PATH);
            if (!Strings.isNullOrEmpty(externalConfigPath)) {
                log.info("External config file specific, began to load, PATH is: " + externalConfigPath);
                File externalConfigFile = new File(externalConfigPath);
                if (externalConfigFile.exists()) {
                    PropertiesConfiguration externalConfig = new PropertiesConfiguration(externalConfigFile.getAbsoluteFile());
                    externalConfig.setReloadingStrategy(new FileChangedReloadingStrategy());
                    compositeConfiguration.addConfiguration(externalConfig);
                    log.info("External config file load successfully, override system default config file");
                }
            }

            //Step 2. load internal config.properties
            compositeConfiguration.addConfiguration(new PropertiesConfiguration("base_config.properties"));
            log.info("System default config file loaded ");

            return compositeConfiguration;
        } catch (ConfigurationException e) {
            log.fatal("Loading config file failed: " + e);
            throw new RuntimeException();
        }
    }

    public static String getProperty(String key) {
        return configuration.getString(key);
    }

    public static List<String> getPropertyList(String key) throws UnsupportedEncodingException {
        String[] propertyArray = configuration.getStringArray(key);

        for (int i = 0; i < propertyArray.length; i++) {
            propertyArray[i] = new String(propertyArray[i].getBytes("ISO-8859-1"), "UTF8");
        }

        return Arrays.asList(propertyArray);
    }

    public static boolean getBoolean(String key) {
        return configuration.getBoolean(key, false);
    }
}


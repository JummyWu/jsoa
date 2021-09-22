package com.github.jummy.log.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author: jummy
 * @create_date: 15/09/2021 9:24 上午
 * @desc:
 */
public class ApplicationLoggerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String logBase = environment.getProperty("logging.path", "/data/projects/logs");
        String appName = environment.getProperty("spring.application.name");
        // spring boot admin 直接加载日志
        // System.setProperty("logging.file", String.format("%s/%s/root.log", logBase, appName));

        //nacos的日志文件路径
        System.setProperty("nacos.logging.path", String.format("%s/%s", logBase, appName));
    }
}

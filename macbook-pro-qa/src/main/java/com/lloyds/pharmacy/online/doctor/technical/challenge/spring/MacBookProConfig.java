package com.lloyds.pharmacy.online.doctor.technical.challenge.spring;

import java.io.IOException;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import com.lloyds.pharmacy.online.doctor.technical.challenge.properties.EnvProperties;
import com.lloyds.pharmacy.online.doctor.technical.challenge.properties.SeleniumProperties;
import com.lloyds.pharmacy.online.doctor.technical.challenge.setup.BaseTest;
import lombok.extern.slf4j.Slf4j;


@Configuration
@ComponentScan({"com.lloyds.pharmacy.online.doctor.technical.challenge"})
@EnableConfigurationProperties({SeleniumProperties.class, EnvProperties.class})
@Slf4j
public class MacBookProConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
        String env = System.getProperty("env", "local");
        log.info("===== Using profile {} =====", env);
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        MutablePropertySources propertySources = new MutablePropertySources();

        YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
        PropertySource<?> macBookProProperties = sourceLoader.load("macBookProProperties", new ClassPathResource("macbook-pro-application.yml"), env);
        propertySources.addFirst(macBookProProperties);

        configurer.setPropertySources(propertySources);
        return configurer;
    }

}

package cz.muni.fi.pa165.carparkmanager.service.config;

import cz.muni.fi.pa165.carparkmanager.persistence.conf.PersistenceApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Roman Nedelka
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(value = "cz.muni.fi.pa165.carparkmanager.service")
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }

}

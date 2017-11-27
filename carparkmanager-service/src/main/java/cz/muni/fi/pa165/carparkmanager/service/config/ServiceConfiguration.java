package cz.muni.fi.pa165.carparkmanager.service.config;

import cz.muni.fi.pa165.carparkmanager.api.dto.CarDTO;
import cz.muni.fi.pa165.carparkmanager.api.dto.DriveDTO;
import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carparkmanager.api.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.carparkmanager.persistence.conf.PersistenceApplicationContext;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import cz.muni.fi.pa165.carparkmanager.service.CarServiceImpl;
import cz.muni.fi.pa165.carparkmanager.service.facade.CarFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
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
@ComponentScan(basePackageClasses = {CarServiceImpl.class, CarFacadeImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }

    public class DozerCustomConfig extends BeanMappingBuilder {

        @Override
        protected void configure() {
            mapping(Car.class, CarDTO.class);
            mapping(Employee.class, EmployeeDTO.class);
            mapping(ServiceCheck.class, ServiceCheckDTO.class);
            mapping(Drive.class, DriveDTO.class);
        }
    }
}

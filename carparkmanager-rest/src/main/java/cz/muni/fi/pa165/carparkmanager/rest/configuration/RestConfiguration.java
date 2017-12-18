package cz.muni.fi.pa165.carparkmanager.rest.configuration;

import cz.muni.fi.pa165.carparkmanager.persistence.conf.PersistenceApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Jakub Juřena <445319>
 */
@Configuration
// TODO import sampleDataContext -> TODO sampleDataContext
@Import(PersistenceApplicationContext.class)
@ComponentScan(value = "cz.muni.fi.pa165.carpark.rest")
public class RestConfiguration extends WebMvcConfigurerAdapter {
    
}

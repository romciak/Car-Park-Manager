package cz.muni.fi.pa165.carparkmanager.sample.data.conf;

import cz.muni.fi.pa165.carparkmanager.sample.data.SampleDataLoaderFacade;
import cz.muni.fi.pa165.carparkmanager.sample.data.SampleDataLoaderFacadeImpl;
import cz.muni.fi.pa165.carparkmanager.service.config.ServiceConfiguration;
import java.text.ParseException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author romciak
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoaderFacadeImpl.class})
public class SampleDataLoaderConf {

    @Autowired
    private SampleDataLoaderFacade sampleDataLoader;

    @PostConstruct
    public void dataLoading() throws ParseException {
        sampleDataLoader.loadSampleData();
    }
}

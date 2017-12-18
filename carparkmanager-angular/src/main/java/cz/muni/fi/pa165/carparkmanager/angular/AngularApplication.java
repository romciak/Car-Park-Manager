package cz.muni.fi.pa165.carparkmanager.angular;

import cz.muni.fi.pa165.carparkmanager.angular.configuration.AngularConfiguration;
import cz.muni.fi.pa165.carparkmanager.rest.configuration.RestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Created by karelfajkus on 14/12/2016.
 */
@Import({RestConfiguration.class, AngularConfiguration.class})
@EnableAutoConfiguration
public class AngularApplication {

    public static void main(String[] args) {
        SpringApplication.run(AngularApplication.class, args);
    }
}

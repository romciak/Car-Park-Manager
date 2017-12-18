package cz.muni.fi.pa165.carparkmanager.angular.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Jakub Juřena <445319>
 */
@ComponentScan(value = "cz.muni.fi.pa165.carparkmanager.angular")
@Configuration
public class AngularConfiguration {

    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // forward request /pa165 to index.html
                registry.addViewController("/pa165").setViewName(
                        "forward:/index.html");
            }
        };
    }
}

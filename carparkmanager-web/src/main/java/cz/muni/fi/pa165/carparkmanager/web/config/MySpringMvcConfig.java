package cz.muni.fi.pa165.carparkmanager.web.config;

import cz.muni.fi.pa165.carparkmanager.sample.data.conf.SampleDataLoaderConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.validation.Validator;

/**
 * The central Spring context and Spring MVC configuration.
 * The @Configuration annotation declares it as Spring configuration.
 * The @EnableWebMvc enables default  MVC config for using @Controller, @RequestMapping and so on,
 * see http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-config-enable
 *
 * @author Martin Kuba makub@ics.muni.cz
 */


@EnableWebMvc
@Configuration
@Import({SampleDataLoaderConf.class})
@ComponentScan(basePackages = "fi.muni.cz.pa165.carparkmanager.controllers.")
public class MySpringMvcConfig extends WebMvcConfigurerAdapter {

    public static final Logger LOGGER = LoggerFactory.getLogger(MySpringMvcConfig.class);

    public static final String TEXTS = "texts";

    /**
     * Maps the main page to a specific view.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        LOGGER.debug("mapping URL / to home view");
        registry.addViewController("/").setViewName("index");
    }


    /**
     * Enables default Tomcat servlet that serves static files.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        LOGGER.debug("enabling default servlet for static files");
        configurer.enable();
    }

    /**
     * Provides mapping from view names to JSP pages in WEB-INF/jsp directory.
     * @return view resolver
     */
    @Bean
    public ViewResolver viewResolver() {
        LOGGER.debug("registering JSP in /WEB-INF/jsp/ as views");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * Provides localized messages.
     * @return message source
     */
    @Bean
    public MessageSource messageSource() {
        LOGGER.debug("registering ResourceBundle 'Texts' for messages");
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(TEXTS);
        return messageSource;
    }

    /**
     * Provides JSR-303 Validator.
     * @return validator
     */
    @Bean
    public Validator validator() {
        LOGGER.debug("registering JSR-303 validator");
        return new LocalValidatorFactoryBean();
    }


}
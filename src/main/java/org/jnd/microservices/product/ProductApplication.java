package org.jnd.microservices.product;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jnd.microservices.product.controller.ProductController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


@ComponentScan
@SpringBootApplication(scanBasePackages={"org.jnd"})
@PropertySources({
        @PropertySource(value = "classpath:config.${spring.profiles.active:default}.properties",  ignoreResourceNotFound = true),
        @PropertySource(value = "file:/config/config.${spring.profiles.active:default}.properties", ignoreResourceNotFound = true)
})
@RestController
@Configuration
@EnableAutoConfiguration
public class ProductApplication extends SpringBootServletInitializer {

    private Log log = LogFactory.getLog(ProductController.class);

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String ping() {
        return "OK";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "OK";
    }

    @PostConstruct
    public void debug() {
        log.info("******** : v2");
    }
}

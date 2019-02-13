package org.jnd.microservices.product.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jnd.microservices.model.Product;
import org.jnd.microservices.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;


@Component("ProductRepository")
@Profile("dev")
public class DefaultProductRepository extends RepositoryBase {

    private Log log = LogFactory.getLog(DefaultProductRepository.class);

    @Value("${sleep.time.ms:0}")
    private int sleepTime = 0;

    @Value("${spring.profiles.active:default}")
    private String profile = null;

    @Autowired
    private ProductCache cache;

    @PostConstruct
    public void init()  {

        log.debug("Setting up repository");

        getTypes().add(ProductType.FOOD.toString());
        getTypes().add(ProductType.CLOTHES.toString());
        getTypes().add(ProductType.GADGETS.toString());
        getProducts().putAll(cache.getFood());
        getProducts().putAll(cache.getClothes());
        getProducts().putAll(cache.getGadgets());

    }


    public Map<String, Product> getProducts() {

        log.debug("Sleep time (ms) : "+sleepTime);
        log.debug("Spring Profile : "+profile);
        sleep(sleepTime);
        return super.getProducts();
    }

    public List<String> getTypes() {

        log.debug("Sleep time (ms) : "+sleepTime);
        log.debug("Spring Profile : "+profile);
        sleep(sleepTime);
        return super.getTypes();
    }

}

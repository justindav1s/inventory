package org.jnd.microservices.product.repository;

import org.jnd.microservices.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component("ProductRepository")
public interface ProductRepository {

    Map<String, Product> getProducts();
    List<String> getTypes();
}

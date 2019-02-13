package org.jnd.microservices.product.repository;

import org.jnd.microservices.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepository {

    Map<String, Product> getProducts();
    List<String> getTypes();
}

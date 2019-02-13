package org.jnd.microservices.product.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jnd.microservices.model.Product;
import org.jnd.microservices.model.ProductType;
import org.jnd.microservices.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    private Log log = LogFactory.getLog(ProductController.class);

    @Autowired
    private ProductRepository repository;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Object[]> getAll(@RequestHeader HttpHeaders headers) {


        log.debug("Product get All");

        Object[] products = repository.getProducts().values().toArray();

        log.debug("Product get types : "+products);

        return new ResponseEntity<>(products, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<String[]> getTypes(@RequestHeader HttpHeaders headers) {

        log.debug("Product get types");

        //this.getB3Headers(headers);

        ArrayList<String> types = (ArrayList)repository.getTypes();

        log.debug("Product get types : "+types);

        return new ResponseEntity(types, null, HttpStatus.OK);
    }


    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Product[]> getProductsOfType(@PathVariable String type, @RequestHeader HttpHeaders headers) {

        log.debug("Product get of type :"+type);

        //this.getB3Headers(headers);

        ArrayList products = new ArrayList();
        for (Product p : repository.getProducts().values()){
            if (p.getType().toString().equalsIgnoreCase(type))   {
                products.add(p);
            }
        }

        return new ResponseEntity(products, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Product> get(@PathVariable Integer productId, @RequestHeader HttpHeaders headers) {


        log.debug("Product get : "+productId);

        Product product = repository.getProducts().get(Integer.toString(productId));

        return new ResponseEntity<>(product, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/create/{productName}/{productType}/{productPrice}", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<Object[]> create(@PathVariable String productName,
                                    @PathVariable String productType,
                                    @PathVariable String productPrice,
                                    @RequestHeader HttpHeaders headers) {

        log.debug("Product Create : "+productName);

        int productCount = repository.getProducts().values().toArray().length;
        int productId = productCount + 1;

        ProductType type = null;
        if (productType.equalsIgnoreCase(ProductType.CLOTHES.toString()))    {
            type = ProductType.CLOTHES;
        }
        else if (productType.equalsIgnoreCase(ProductType.FOOD.toString()))    {
            type = ProductType.FOOD;
        }
        else if (productType.equalsIgnoreCase(ProductType.GADGETS.toString()))    {
            type = ProductType.GADGETS;
        }

        Product p = new Product(Integer.toString(productId), productName, type, Float.valueOf(productPrice));

        repository.getProducts().put(Integer.toString(productId), p);

        log.debug("Product Created : "+p);

        Object[] products = repository.getProducts().values().toArray();

        return new ResponseEntity<>(products, null, HttpStatus.CREATED);
    }
}

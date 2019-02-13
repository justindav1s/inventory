package org.jnd.microservices.product.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@RestController
@RequestMapping("/config")
public class ConfigController {

    private Log log = LogFactory.getLog(ProductController.class);

    @Value("${my.property:not_found}")
    private String property1 = null;
    @Value("${sleep.time.ms:not_found}")
    private String sleepTime = "0";
    @Value("${inventory.version:not_found}")
    private String version;


    @PostConstruct
    public void init()  {
        log.debug("init");
    }

    @RequestMapping(value = "/p1", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HashMap> config() {

        HashMap map = new HashMap();
        map.put("my.property", property1);
        map.put("sleepTime", sleepTime);
        map.put("version", version);

        return new ResponseEntity<>(map, null, HttpStatus.OK);
    }
}

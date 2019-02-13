package org.jnd.microservices.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jnd.microservices.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ProductApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@ActiveProfiles("v2")
public class ProductControllerV2IntTest {

	private Log log = LogFactory.getLog(ProductControllerV2IntTest.class);

	@Autowired
	private MockMvc mvc;

	@Test
	public void getProductsTypesTest200()
			throws Exception {

        MvcResult result = mvc.perform(get("/products/types")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String json = result.getResponse().getContentAsString();
        log.debug("result : " + json);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList types = mapper.readValue(json, ArrayList.class);
        assertNotNull(types);
	}

    @Test
    public void getProductsAllTest200()
            throws Exception {

        MvcResult result = mvc.perform(get("/products/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String json = result.getResponse().getContentAsString();
        log.debug("result : " + json);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Product> products;
        products = mapper.readValue(json, new TypeReference<ArrayList<Product>>() {});
        assertNotNull(products);
        assertTrue(products.size() >5);
        assertTrue((Product)products.get(0) instanceof Product);
        Product p = (Product)products.get(0);
        assertNotNull(p);
    }
}

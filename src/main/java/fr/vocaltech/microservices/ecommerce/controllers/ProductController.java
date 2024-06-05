package fr.vocaltech.microservices.ecommerce.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import fr.vocaltech.microservices.ecommerce.daos.ProductRepository;
import fr.vocaltech.microservices.ecommerce.exceptions.ProductNotFoundException;
import fr.vocaltech.microservices.ecommerce.models.Product;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ProductController {
    private static Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/Products")
    public MappingJacksonValue findAll() {
        var products = productRepository.findAll();
        return applyFilter(products);
    }
    @GetMapping("/Products/{id}")
    public MappingJacksonValue findById(@PathVariable long id) {
        var singleProduct = productRepository.findById(id);

        if (singleProduct == null)
            throw new ProductNotFoundException("Product with id=" + id + " not found !");

        return applyFilter(singleProduct);
    }

    @GetMapping("/Products/greater/{price}")
    public MappingJacksonValue findByPriceGreaterThan(@PathVariable double price) {
        var products = productRepository.findByPriceGreaterThan(price);
        return applyFilter(products);
    }
    @PostMapping("/Products")
    public void createProduct(@Valid @RequestBody Product product) {
        productRepository.save(product);
    }
    @PutMapping("/Products")
    public void updateProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    @DeleteMapping("/Products/{id}")
    public void removeProduct(@PathVariable long id) {
        productRepository.deleteById(id);
    }

    @GetMapping(value = "/AdminProducts")
    public HashMap<Product, Double> productMargin() {
        HashMap<Product, Double> hm = new HashMap<>();
        var products = productRepository.findAll();
        products.forEach(product -> {
            double marge = product.getPrice() - product.getBuyPrice();
            log.info(String.format("%s: %f", product.toString(), marge));
            hm.put(product, marge);
        });
        return hm;
    }
    @GetMapping(value = "/SortProducts")
    public List<Product> findAllSortByName() {
        return productRepository.findAllByOrderByNameAsc();
    }
    private MappingJacksonValue applyFilter(Object object) {
        SimpleBeanPropertyFilter buyPriceFilter = SimpleBeanPropertyFilter.serializeAllExcept("buyPrice");
        FilterProvider filters = new SimpleFilterProvider().addFilter("dynamicFilter", buyPriceFilter);
        MappingJacksonValue filteredProducts = new MappingJacksonValue(object);
        filteredProducts.setFilters(filters);

        return filteredProducts;
    }
}

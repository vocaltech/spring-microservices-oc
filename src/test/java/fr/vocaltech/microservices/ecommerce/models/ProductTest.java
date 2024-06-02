package fr.vocaltech.microservices.ecommerce.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ProductTest {
    @Test
    void givenProductParams_whenGet_thenOk() {
        var product = new ProductRecord(1, "Product 1", 369.0, 269.0);

        assertEquals(1, product.id());
        assertEquals("Product 1", product.name());
        assertEquals(369.0, product.price());
        assertEquals(269.0, product.buyPrice());

    }
}
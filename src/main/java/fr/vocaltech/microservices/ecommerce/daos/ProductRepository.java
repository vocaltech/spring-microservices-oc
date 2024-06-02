package fr.vocaltech.microservices.ecommerce.daos;

import fr.vocaltech.microservices.ecommerce.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findById(long id);
    List<Product> findByPriceGreaterThan(double price);
    List<Product> findByBuyPriceGreaterThan(double price);
}

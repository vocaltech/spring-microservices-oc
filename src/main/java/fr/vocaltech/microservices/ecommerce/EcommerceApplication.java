package fr.vocaltech.microservices.ecommerce;

import fr.vocaltech.microservices.ecommerce.daos.ProductRepository;
import fr.vocaltech.microservices.ecommerce.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EcommerceApplication {
	private final static Logger log = LoggerFactory.getLogger(EcommerceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository repository) {
		return args -> {
			// save some products
			List<Product> products = List.of(
					new Product("Prod1", 369.0, 269),
					new Product("Prod2", 3369.1, 3269),
					new Product("Prod3", 3669.2, 3559)
			);
			repository.saveAll(products);

			// fetch all products
			repository.findAll().forEach(product -> log.info(product.toString()));

			// findById
			log.info(repository.findById(3).toString());

			// findByPriceGreaterThan
			repository.findByPriceGreaterThan(3300).forEach(product -> log.info(product.toString()));

			// findByBuyPriceGreaterThan
			repository.findByBuyPriceGreaterThan(3300).forEach(product -> log.info(product.toString()));
		};
	}
}

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
					new Product("Ordinateur portable" , 350, 120),
					new Product("Aspirateur Robot" , 500, 200),
					new Product("Table de Ping Pong" , 750, 400));

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

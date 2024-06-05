package fr.vocaltech.microservices.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductPriceException extends RuntimeException {
    public ProductPriceException(String s) {
        super(s);
    }
}

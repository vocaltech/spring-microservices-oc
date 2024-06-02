package fr.vocaltech.microservices.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@JsonFilter("dynamicFilter")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 25)
    private String name;

    @Min(value = 1)
    private double price;

    @Column(name = "buy_price")
    @Min(value = 1)
    private double buyPrice;

    protected Product() {}

    public Product(String name, double price, double buyPrice) {
        this.name = name;
        this.price = price;
        this.buyPrice = buyPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", buyPrice=" + buyPrice +
                '}';
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getBuyPrice() {
        return buyPrice;
    }
}

package fr.vocaltech.microservices.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("dynamicFilter")
public record ProductRecord(int id, String name, double price, double buyPrice){}

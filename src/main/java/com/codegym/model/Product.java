package com.codegym.model;

import lombok.Data;
import org.hibernate.engine.internal.ImmutableEntityEntry;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import javax.validation.constraints.*;


@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @NotEmpty(message = "Enter the product code")
    public String id;

    @NotEmpty(message = "Enter the type name")
    public String type;

    @NotEmpty(message = "Enter the product name ")
    public String name;

    @NotNull(message = "Enter the brand name")
    public String brand;

    @NotNull(message = "Enter product price")
    @Min(value = 10)
    public int price;

    @NotEmpty(message = "Add image product")
    public String image;

    @NotNull(message = "Enter amount")
    @Min(value = 1)
    public int amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

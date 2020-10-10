package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @NotEmpty
    @Column(columnDefinition = "varchar(15)")
    public String id;
    public String type;
    public String name;
    public String brand;
    public int price;
    public String image;
    public int amount;

    public Product() {
    }
}

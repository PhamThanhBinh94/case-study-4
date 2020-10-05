package com.codegym.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @NotEmpty
    public String id;
    public String type;
    public String name;
    public String brand;
    public int price;
    public String image;
    public int amount;
}

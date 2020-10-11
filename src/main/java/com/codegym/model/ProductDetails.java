package com.codegym.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_details")
@Data
public class ProductDetails {

    @Id
    @Column(columnDefinition = "varchar(15)")
    private String productId;

    @Column(columnDefinition = "longtext")
    private String feature;

    @Column(columnDefinition = "longtext")
    private String technology;

    @Column(columnDefinition = "longtext")
    private String generalInfo;
}

package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String product_id;

    @NotNull
    private String customer_name;
    private String customer_email;

    @NotNull
    private String customer_phone;
    private Date date;

    @NotNull
    private int rate;
}

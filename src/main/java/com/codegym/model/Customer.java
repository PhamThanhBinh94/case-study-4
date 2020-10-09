package com.codegym.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @NotEmpty
    private String phone;
    private String name;
    private String email;
    private String address;
}

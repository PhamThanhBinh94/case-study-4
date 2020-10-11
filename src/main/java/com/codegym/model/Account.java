package com.codegym.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;


    private String username;
    private String password;

    @ManyToOne
    private  Role role;
}

package com.codegym.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Embedded;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @NotBlank(message = "Please enter the phone number")
    @Pattern(regexp = "^[0][6789][0-9]{8}", message = "Please re-enter your phone number")
    private String phone;

    @NotBlank(message = "Please enter your full name")
    private String name;

    @NotBlank(message = "Please enter your email")
    @Email
    private String email;

    @NotBlank(message = "Please enter your address")
    @Size(min = 2, max = 250)
    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

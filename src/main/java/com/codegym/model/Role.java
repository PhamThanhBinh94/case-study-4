package com.codegym.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long roleId;
    private  String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}

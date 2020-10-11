package com.codegym.model;

import lombok.Data;

@Data
public class mailRequest {
    private String name;
    private String to;
    private String form;
    private String subject;
}

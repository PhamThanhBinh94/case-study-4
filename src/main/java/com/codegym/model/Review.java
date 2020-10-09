package com.codegym.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String productId;

    @NotNull
    private String customerName;
    private String customerEmail;

    @NotNull
    private String customerPhone;

    @NotNull
    @Column(columnDefinition = "text")
    private String content;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date date;

    @NotNull
    private int rate;

    public Review(Long id, @NotNull String productId, @NotNull String customerName, String customerEmail, @NotNull String customerPhone, @NotNull String content, @NotNull Date date, @NotNull int rate) {
        this.id = id;
        this.productId = productId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.content = content;
        this.date = date;
        this.rate = rate;
    }
}

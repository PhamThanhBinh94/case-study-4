package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Properties;

@Entity
@Data
@Table(name="bill_detail")
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billDetail_Id;

//    @ManyToOne
    @JoinColumn(name = "bill_id")
    @Column(name="bill_id")
//    @JoinColumn(name = "bill_id", foreignKey = @ForeignKey(name="bill_detail_ibfk_1"))
    private Long billId;

//    @ManyToOne
    @JoinColumn(name = "product_id")
    @Column(name="product_id")
//    @JoinColumn(name= "id", foreignKey = @ForeignKey(name="bill_detail_ibfk_2"))
    private String productId;

    private Integer unit_price;

    private Integer amount;
}

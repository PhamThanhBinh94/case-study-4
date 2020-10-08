package com.codegym.model;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="bill")
public class Bill {
    @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bill_id")
    private Long billId;

    @Column(name="customer_id")
    private String customerId;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date date;
    private String address;
    private String status;

    //    @OneToMany(targetEntity = BillDetail.class)
    //    private List<BillDetail> billDetails;
}

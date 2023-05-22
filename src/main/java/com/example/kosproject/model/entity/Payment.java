package com.example.kosproject.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "payment")
@Getter @Setter
@ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "tenancy_id", referencedColumnName = "tenancy_id")
    private Tenancy tenancy;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_amount")
    private Integer paymentAmount;
}

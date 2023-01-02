package com.example.rediscache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue
    private Long id;

    private String orderCode;
    private String orderObject;
    private String orderStatus;
    private int orderPrice;
}

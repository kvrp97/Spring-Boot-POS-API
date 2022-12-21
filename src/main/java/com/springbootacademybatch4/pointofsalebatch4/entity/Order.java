package com.springbootacademybatch4.pointofsalebatch4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    @Column(name = "order_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

//  one order has one customer only
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    @Column(name = "order_date", columnDefinition = "DATETIME")
    private Date date;

    @Column(name = "total", nullable = false)
    private double total;

//  one order has many orderDetails
    @OneToMany(mappedBy="order")
    private Set<OrderDetails> orderDetails;

    public Order(Customer customer, Date date, double total) {
        this.customer = customer;
        this.date = date;
        this.total = total;
    }
}

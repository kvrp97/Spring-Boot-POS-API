package com.springbootacademybatch4.pointofsalebatch4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {
    @Id
    @Column(name = "order_details_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderDetailsId;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Column(name = "qty", length = 50, nullable = false)
    private double qty;

    @Column(name = "amount", nullable = false)
    private double amount;

    // one orderDetails has one order only
    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    // one orderDetails has one item only
    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item item;

}

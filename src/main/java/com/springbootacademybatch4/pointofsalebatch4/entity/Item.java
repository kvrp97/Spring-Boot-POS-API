package com.springbootacademybatch4.pointofsalebatch4.entity;

import com.springbootacademybatch4.pointofsalebatch4.entity.enums.MeasuringUnitType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
//@Getter
//@Setter
//@ToString
@Data
public class Item {

    @Id
    @Column(name = "item_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "measure_type", length = 25, nullable = false)
    private MeasuringUnitType measuringUnitType;

    @Column(name = "balance_qty", length = 50, nullable = false)
    private double balanceQty;

    @Column(name = "supplier_price", length = 50, nullable = false)
    private double supplierPrice;

    @Column(name = "selling_price", length = 50, nullable = false)
    private double sellingPrice;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean activeState;

//    one item has many orderDetails
    @OneToMany(mappedBy="item")
    private Set<OrderDetails> orderDetails;
}

package com.springbootacademybatch4.pointofsalebatch4.dto.request;

import com.springbootacademybatch4.pointofsalebatch4.entity.Item;
import com.springbootacademybatch4.pointofsalebatch4.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSaveDTO {
    private String itemName;
    private double qty;
    private double amount;
    private int item;    // we take item id from frontend

//    private Order order;
//    private Item item;

}

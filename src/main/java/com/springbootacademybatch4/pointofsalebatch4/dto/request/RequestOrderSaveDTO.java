package com.springbootacademybatch4.pointofsalebatch4.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {
    private int customer;
    private Date date;
    private double total;
    private boolean activeState;
    private List<RequestOrderDetailsSaveDTO> orderDetails;
}

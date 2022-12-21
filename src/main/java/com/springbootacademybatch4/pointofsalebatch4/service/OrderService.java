package com.springbootacademybatch4.pointofsalebatch4.service;

import com.springbootacademybatch4.pointofsalebatch4.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);
}

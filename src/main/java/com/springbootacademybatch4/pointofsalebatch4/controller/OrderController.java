package com.springbootacademybatch4.pointofsalebatch4.controller;

import com.springbootacademybatch4.pointofsalebatch4.dto.request.RequestOrderSaveDTO;
import com.springbootacademybatch4.pointofsalebatch4.service.OrderService;
import com.springbootacademybatch4.pointofsalebatch4.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO){

        String id = orderService.addOrder(requestOrderSaveDTO);

        return new ResponseEntity<>(
                new StandardResponse(201,id+" success",id),
                HttpStatus.CREATED
        );
    }
}

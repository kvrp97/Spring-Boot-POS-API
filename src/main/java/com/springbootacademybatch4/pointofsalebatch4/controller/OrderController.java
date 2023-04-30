package com.springbootacademybatch4.pointofsalebatch4.controller;

import com.springbootacademybatch4.pointofsalebatch4.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademybatch4.pointofsalebatch4.dto.request.RequestOrderSaveDTO;
import com.springbootacademybatch4.pointofsalebatch4.service.OrderService;
import com.springbootacademybatch4.pointofsalebatch4.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

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

    @GetMapping(
            params = {"stateType","page","size"},
            path = {"/get-order-details"}
    )
    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam(value = "stateType") String stateType,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ){
        PaginatedResponseOrderDetails paginatedResponseOrderDetails = null;

        if (stateType.equalsIgnoreCase("active") | stateType.equalsIgnoreCase("inactive")) {
            boolean status = stateType.equalsIgnoreCase("active") ? true : false;
            paginatedResponseOrderDetails = orderService.getAllOrderDetails(status, page, size);
        }
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"success", paginatedResponseOrderDetails),
                HttpStatus.OK
        );
    }
}

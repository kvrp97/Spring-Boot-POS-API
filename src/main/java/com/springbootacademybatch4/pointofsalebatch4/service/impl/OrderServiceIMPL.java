package com.springbootacademybatch4.pointofsalebatch4.service.impl;

import com.springbootacademybatch4.pointofsalebatch4.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademybatch4.pointofsalebatch4.dto.queryInterface.OrderDetailsInterface;
import com.springbootacademybatch4.pointofsalebatch4.dto.request.RequestOrderSaveDTO;
import com.springbootacademybatch4.pointofsalebatch4.dto.response.ResponseOrderDetailsDTO;
import com.springbootacademybatch4.pointofsalebatch4.entity.Order;
import com.springbootacademybatch4.pointofsalebatch4.entity.OrderDetails;
import com.springbootacademybatch4.pointofsalebatch4.repo.CustomerRepo;
import com.springbootacademybatch4.pointofsalebatch4.repo.ItemRepo;
import com.springbootacademybatch4.pointofsalebatch4.repo.OrderDetailsRepo;
import com.springbootacademybatch4.pointofsalebatch4.repo.OrderRepo;
import com.springbootacademybatch4.pointofsalebatch4.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order = new Order(
                customerRepo.getById(requestOrderSaveDTO.getCustomer()),
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal()
        );
        orderRepo.save(order);

        if (orderRepo.existsById(order.getOrderId())){
            List<OrderDetails> orderDetails = modelMapper
                    .map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>(){
                    }.getType());

            for (int i = 0; i < orderDetails.size(); i++){
                orderDetails.get(i).setOrder(order);
                orderDetails.get(i).setItem(itemRepo.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItem()));
            }

            if (orderDetails.size()>0){
                orderDetailsRepo.saveAll(orderDetails);
            }
            return "Order Saved";
        }
        return null;
    }

    @Override
    public PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size) {
        List<OrderDetailsInterface> orderDetailsInterfaces = orderRepo.getAllOrderDetails(status, PageRequest.of(page, size));
//        System.out.println(orderDetailsInterfaces.get(0).getCustomerName());

        List<ResponseOrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        for (OrderDetailsInterface o : orderDetailsInterfaces) {
//            ResponseOrderDetailsDTO responseOrderDetailsDTO = new ResponseOrderDetailsDTO(
//                    o.getCustomerName(),o.getCustomerAddress(),o.getContactNumbers(),o.getDate(),o.getTotal()
//            );
//            orderDetailsDTOList.add(responseOrderDetailsDTO);

            orderDetailsDTOList.add(
                    new ResponseOrderDetailsDTO(
                            o.getCustomerName(),o.getCustomerAddress(),o.getContactNumbers(),o.getDate(),o.getTotal()
                    )
            );
        }
        PaginatedResponseOrderDetails paginatedResponseOrderDetails = new PaginatedResponseOrderDetails(
                orderDetailsDTOList,
                orderRepo.countAllOrderDetails(status)
        );

        return paginatedResponseOrderDetails;
    }
}

package com.springbootacademybatch4.pointofsalebatch4.service.impl;

import com.springbootacademybatch4.pointofsalebatch4.dto.CustomerDTO;
import com.springbootacademybatch4.pointofsalebatch4.entity.Customer;
import com.springbootacademybatch4.pointofsalebatch4.repo.CustomerRepo;
import com.springbootacademybatch4.pointofsalebatch4.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addCustomer(CustomerDTO customerDTO) {
//        Customer customer = new Customer(
//                customerDTO.getCustomerId(),
//                customerDTO.getCustomerName(),
//                customerDTO.getCustomerAddress(),
//                customerDTO.getCustomerSalary(),
//                customerDTO.getContactNumbers(),
//                customerDTO.getNic(),
//                customerDTO.isActiveState()
//        );

        Customer customer = modelMapper.map(customerDTO, Customer.class);

        if (!customerRepo.existsById(customer.getCustomerId())) {
            customerRepo.save(customer);
        } else {
            System.out.println("Customer id already exists");
        }

    }

    @Override
    public String updateCustomer(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getCustomerId())){
            Customer customer = customerRepo.getById(customerDTO.getCustomerId());

            customer.setCustomerName(customerDTO.getCustomerName());
            customer.setCustomerAddress(customerDTO.getCustomerAddress());
            customer.setCustomerSalary(customerDTO.getCustomerSalary());

            customerRepo.save(customer);
            return "Updated";
        } else {
            System.out.println("No customer found for that ID");
            return "No customer found for that ID";
        }
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        Customer customer = customerRepo.getById(customerId);
        //using model mapper
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;


//        Customer customer = customerRepo.getById(customerId);
//        if (customer!=null){
//            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.getCustomerId(),
//                    customer.getCustomerName(),
//                    customer.getCustomerAddress(),
//                    customer.getCustomerSalary(),
//                    customer.getContactNumbers(),
//                    customer.getNic(),
//                    customer.isActiveState()
//            );
//            return customerDTO;
//        } else {
//            return null;
//        }

//        Optional<Customer> customer = customerRepo.findById(customerId);
//        if (customer.isPresent()){
//            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.get().getCustomerId(),
//                    customer.get().getCustomerName(),
//                    customer.get().getCustomerAddress(),
//                    customer.get().getCustomerSalary(),
//                    customer.get().getContactNumbers(),
//                    customer.get().getNic(),
//                    customer.get().isActiveState()
//            );
//            return customerDTO;
//        } else {
//            return null;
//        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getCustomers = customerRepo.findAll();
//        Customer customer1 = getCustomers.get(0);

//        List<CustomerDTO> customerDTOList = new ArrayList<>();

//        for (Customer customer : getCustomers){
//            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.getCustomerId(),
//                    customer.getCustomerName(),
//                    customer.getCustomerAddress(),
//                    customer.getCustomerSalary(),
//                    customer.getContactNumbers(),
//                    customer.getNic(),
//                    customer.isActiveState()
//            );
//            customerDTOList.add(customerDTO);
//        }

        if (!getCustomers.isEmpty()) {
            List<CustomerDTO> customerDTOList = modelMapper
                    .map(getCustomers, new TypeToken<List<CustomerDTO>>(){
                    }.getType());
            return customerDTOList;
        } else {
            return null;
        }
    }

    @Override
    public String deleteCustomer(int customerId) {
        if (customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);
            return "Deleted";
        } else {
            return "No customer found for that Id";
        }
    }

}

package com.basry.customerservice.web;

import com.basry.customerservice.dto.CustomerRequestDTO;
import com.basry.customerservice.dto.CustomerResponseDTO;
import com.basry.customerservice.entities.Customer;
import com.basry.customerservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CustomerRestController {
    private CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping(path = "/customers")
    public List<CustomerResponseDTO> allCustomers(){
        return customerService.listCustomers();
    }
    @GetMapping(path = "/customers/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable(name = "id") String id){
        return customerService.getCustomer(id);
    }
    @PostMapping(path = "/customers")
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO){
        return customerService.save(customerRequestDTO);
    }
}

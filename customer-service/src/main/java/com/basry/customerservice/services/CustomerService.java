package com.basry.customerservice.services;

import com.basry.customerservice.dto.CustomerRequestDTO;
import com.basry.customerservice.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService  {
     CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
     CustomerResponseDTO getCustomer(String id);
     CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO);
     List<CustomerResponseDTO> listCustomers();
}

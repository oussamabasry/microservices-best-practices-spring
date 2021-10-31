package com.basry.customerservice.mappers;

import com.basry.customerservice.dto.CustomerRequestDTO;
import com.basry.customerservice.dto.CustomerResponseDTO;
import com.basry.customerservice.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);
    Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO);
}

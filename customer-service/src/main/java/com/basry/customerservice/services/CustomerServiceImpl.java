package com.basry.customerservice.services;

import com.basry.customerservice.dto.CustomerRequestDTO;
import com.basry.customerservice.dto.CustomerResponseDTO;
import com.basry.customerservice.entities.Customer;
import com.basry.customerservice.mappers.CustomerMapper;
import com.basry.customerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements  CustomerService{
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        customerRequestDTO.setId(UUID.randomUUID().toString());
        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(savedCustomer);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO getCustomer(String id) {
        Customer customer = customerRepository.findById(id).get();
        CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(customer);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
        Customer updatedCustomer = customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(updatedCustomer);
        return customerResponseDTO;
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDTO> customerResponseDTOS = customers.stream().map(customer ->
            customerMapper.customerToCustomerResponseDTO(customer)).collect(Collectors.toList());
        return customerResponseDTOS;
    }
}

package com.basry.billingservice.services;

import com.basry.billingservice.dto.InvoiceRequestDTO;
import com.basry.billingservice.dto.InvoiceResponseDTO;
import com.basry.billingservice.entities.Invoice;
import com.basry.billingservice.exceptions.CustomerNotFoundException;
import com.basry.billingservice.mappers.InvoiceMapper;
import com.basry.billingservice.models.Customer;
import com.basry.billingservice.openfeign.CustomerRestClient;
import com.basry.billingservice.repositories.InvoiceRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        Customer customer;
        try{
            customer = customerRestClient.customerById(invoiceRequestDTO.getCustomerID());
        }catch (Exception e){
            throw new CustomerNotFoundException("Customer not found");
        }
        Invoice invoice = invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice savedInvoice = invoiceRepository.save(invoice);
        savedInvoice.setCustomer(customer);
        InvoiceResponseDTO invoiceResponseDTO = invoiceMapper.fromInvoice(savedInvoice);
       return invoiceResponseDTO;
    }

    @Override
    public InvoiceResponseDTO getInvoice(String id) {
        Customer customer;
        Invoice invoice = invoiceRepository.findById(id).get();
        try{
            customer = customerRestClient.customerById(invoice.getCustomerID());
        }catch (Exception e){
            throw new CustomerNotFoundException("Customer not found");
        }
        invoice.setCustomer(customer);
        InvoiceResponseDTO invoiceResponseDTO = invoiceMapper.fromInvoice(invoice);
        return invoiceResponseDTO;
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomer(String customerID) {
        List<Invoice> invoices = invoiceRepository.findByCustomerID(customerID);
        return invoices.stream().map(invoice ->{
            Customer customer = customerRestClient.customerById(invoice.getCustomerID());
            invoice.setCustomer(customer);
           return invoiceMapper.fromInvoice(invoice);
        }).collect(Collectors.toList());
    }
    @Override
    public List<InvoiceResponseDTO> allInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream().map(invoice ->{
            Customer customer = customerRestClient.customerById(invoice.getCustomerID());
            if(customer != null){
                invoice.setCustomer(customer);
                return invoiceMapper.fromInvoice(invoice);
            }
           return  null;
        }).collect(Collectors.toList());
    }
}

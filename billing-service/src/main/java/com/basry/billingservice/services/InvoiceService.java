package com.basry.billingservice.services;

import com.basry.billingservice.dto.InvoiceRequestDTO;
import com.basry.billingservice.dto.InvoiceResponseDTO;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO getInvoice(String id);
    List<InvoiceResponseDTO> invoicesByCustomer(String customerID);
    List<InvoiceResponseDTO> allInvoices();
}

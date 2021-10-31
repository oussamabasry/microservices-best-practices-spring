package com.basry.billingservice.mappers;

import com.basry.billingservice.dto.InvoiceRequestDTO;
import com.basry.billingservice.dto.InvoiceResponseDTO;
import com.basry.billingservice.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoiceRequestDTO(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO fromInvoice(Invoice invoice);
}

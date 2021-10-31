package com.basry.billingservice.dto;

import com.basry.billingservice.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class InvoiceRequestDTO {
    private BigDecimal amount;
    private String customerID;
}

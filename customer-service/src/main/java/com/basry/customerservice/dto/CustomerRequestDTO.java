package com.basry.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerRequestDTO {
    private String id;
    private  String email;
    private String name;
}

package com.emlakjet.ejcase.model.bill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillInfoResponse {

    private String firstName;
    private String lastName;
    private String email;
    private double amount;
    private String productName;
    private String billNo;

}

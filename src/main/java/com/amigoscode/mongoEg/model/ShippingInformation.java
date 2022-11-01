package com.amigoscode.mongoEg.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "ShippingInformation")
public class ShippingInformation {
    @Id
    private String productId;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address address;
    private List<String> productList;
    private BigDecimal totalSpent;
    private LocalDateTime purchaseTime;

    public ShippingInformation(String firstName, String lastName, String email, Gender gender, Address address, List<String> productList, BigDecimal totalSpent, LocalDateTime purchaseTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.productList = productList;
        this.totalSpent = totalSpent;
        this.purchaseTime = purchaseTime;
    }
}

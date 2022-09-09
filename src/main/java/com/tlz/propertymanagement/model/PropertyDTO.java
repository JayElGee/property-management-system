package com.tlz.propertymanagement.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDTO {

    private String title;
    private String description;
    private String ownerName;
    private String ownerEmail;
    private String ownerPhoneNumber;
    private Double price;
    private String address;



}

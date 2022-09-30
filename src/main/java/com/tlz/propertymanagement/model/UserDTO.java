package com.tlz.propertymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long id;
    private String ownerName;
    @NotNull(message = "Must enter an email address")
    @NotEmpty(message = "Email cannot be empty")
    @Size(min = 1, max = 50, message = "Email must be between 1 to 50 characters")
    private String ownerEmail;
    private String phone;
    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Must enter a password")
    private String password;
}

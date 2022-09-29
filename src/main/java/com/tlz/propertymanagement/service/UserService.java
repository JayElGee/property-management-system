package com.tlz.propertymanagement.service;

import com.tlz.propertymanagement.model.UserDTO;

public interface UserService {

     UserDTO register(UserDTO userDTO);
     UserDTO login(String email, String password);
}

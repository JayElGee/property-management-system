package com.tlz.propertymanagement.service.implementation;

import com.tlz.propertymanagement.converter.UserConverter;
import com.tlz.propertymanagement.entity.UserEntity;
import com.tlz.propertymanagement.model.UserDTO;
import com.tlz.propertymanagement.repository.UserRepository;
import com.tlz.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertDTOToEntity(userDTO);
        userRepository.save(userEntity);
        userDTO = userConverter.convertEntityToDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        return null;
    }
}

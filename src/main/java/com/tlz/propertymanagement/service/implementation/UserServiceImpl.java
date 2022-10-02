package com.tlz.propertymanagement.service.implementation;

import com.tlz.propertymanagement.converter.UserConverter;
import com.tlz.propertymanagement.entity.AddressEntity;
import com.tlz.propertymanagement.entity.UserEntity;
import com.tlz.propertymanagement.exception.BusinessException;
import com.tlz.propertymanagement.exception.ErrorModel;
import com.tlz.propertymanagement.model.UserDTO;
import com.tlz.propertymanagement.repository.AddressRepository;
import com.tlz.propertymanagement.repository.UserRepository;
import com.tlz.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> optUe = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if (optUe.isPresent()) {
            List<ErrorModel> errorModelList = new ArrayList<>();

            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("email_already_exist");
            errorModel.setMessage("This email is already associated with an account");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }


        UserEntity userEntity = userConverter.convertDTOToEntity(userDTO);
        userRepository.save(userEntity);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setHouseNumber(userDTO.getHouseNumber());
        addressEntity.setCity(userDTO.getCity());
        addressEntity.setPostalCode(userDTO.getPostalCode());
        addressEntity.setStreet(userDTO.getStreet());
        addressEntity.setCountry(userDTO.getCountry());
        addressEntity.setUserEntity(userEntity);

        addressRepository.save(addressEntity);
        userDTO = userConverter.convertEntityToDTO(userEntity);


        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> optUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);

        if (optUserEntity.isPresent()) {
            userDTO = userConverter.convertEntityToDTO(optUserEntity.get());
        } else {
            List<ErrorModel> errorModelList = new ArrayList<>();

            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("invalid_login");
            errorModel.setMessage("Incorrect Email or Password");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        return userDTO;
    }
}

package com.tlz.propertymanagement.service.implementation;

import com.tlz.propertymanagement.converter.PropertyConverter;
import com.tlz.propertymanagement.entity.PropertyEntity;
import com.tlz.propertymanagement.model.PropertyDTO;
import com.tlz.propertymanagement.repository.PropertyRepository;
import com.tlz.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe = propertyConverter.convertDTOToEntity(propertyDTO);
        pe = propertyRepository.save(pe);

        propertyDTO = propertyConverter.convertEntityToDTO(pe);

        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> listOfProps = (List<PropertyEntity>)propertyRepository.findAll();
        List<PropertyDTO> propList = new ArrayList<>();

        for(PropertyEntity pe: listOfProps) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propList.add(dto);
        }

        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optionalEnt = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;

        if (optionalEnt.isPresent()) {
            PropertyEntity pe = optionalEnt.get();

            pe.setTitle(propertyDTO.getTitle());
            pe.setDescription(propertyDTO.getDescription());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setAddress(propertyDTO.getAddress());
            pe.setPrice(propertyDTO.getPrice());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEnt = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;

        if (optionalEnt.isPresent()) {
            PropertyEntity pe = optionalEnt.get();

            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEnt = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;

        if (optionalEnt.isPresent()) {
            PropertyEntity pe = optionalEnt.get();

            pe.setPrice(propertyDTO.getPrice());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}

package com.tlz.propertymanagement.repository;

import com.tlz.propertymanagement.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;


public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

}

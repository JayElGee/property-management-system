package com.tlz.propertymanagement.repository;

import com.tlz.propertymanagement.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}

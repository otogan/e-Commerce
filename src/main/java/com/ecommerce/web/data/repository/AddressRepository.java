package com.ecommerce.web.data.repository;

import com.ecommerce.web.data.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}

package com.ecommerce.web.data.repository;

import com.ecommerce.web.data.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}

package com.ecommerce.web.data.repository;

import com.ecommerce.web.data.entity.OrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Integer> {
}

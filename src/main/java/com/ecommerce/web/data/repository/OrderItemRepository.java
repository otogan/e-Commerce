package com.ecommerce.web.data.repository;

import com.ecommerce.web.data.entity.OrderItem;
import com.ecommerce.web.data.entity.OrderItemId;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemId> {
}

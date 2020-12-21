package com.egen.order.repository;

import com.egen.order.model.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulkOrderRepository extends JpaRepository<Order,Long> {
}

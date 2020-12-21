package com.egen.order.repository;

import com.egen.order.model.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BulkOrderRepository extends JpaRepository<Order, String> {
}

package com.menu.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.menu.demo.Models.Order;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface OrderRepository extends JpaRepository<Order ,Long> {
	@Query("""
			SELECT COALESCE(SUM(o.totalPrice), 0)
			FROM Order o
			WHERE o.orderStatus = 'DELIVERED'
			""")
			BigDecimal getTotalProfit();
	@Query("""
			SELECT COALESCE(SUM(o.totalPrice), 0)
			FROM Order o
			WHERE o.orderStatus = 'DELIVERED'
			AND o.createdAt BETWEEN :start AND :end
			""")
			BigDecimal getProfitBetween(LocalDateTime start, LocalDateTime end);
	

}

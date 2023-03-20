package com.PS.payrollapplication.Repository;

import com.PS.payrollapplication.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}

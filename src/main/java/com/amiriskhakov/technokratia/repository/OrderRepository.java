package com.amiriskhakov.technokratia.repository;

import com.amiriskhakov.technokratia.entity.Order;
import com.amiriskhakov.technokratia.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findOrdersByUserEmail(String email);

    Optional<Order> findOrderById(int id);

    Optional<List<Order>> getAllBy();

    Optional<List<Order>> findOrdersByCreateDateBetween(LocalDateTime createDate, LocalDateTime createDate2);


}

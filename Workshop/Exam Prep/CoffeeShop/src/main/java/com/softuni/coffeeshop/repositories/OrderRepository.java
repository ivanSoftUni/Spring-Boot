package com.softuni.coffeeshop.repositories;

import com.softuni.coffeeshop.models.entities.Category;
import com.softuni.coffeeshop.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findAllByOrderByPriceDesc();
}

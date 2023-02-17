package com.softuni.coffeeshop.services;

import com.softuni.coffeeshop.models.dtos.AddOrderDto;
import com.softuni.coffeeshop.models.entities.Category;
import com.softuni.coffeeshop.models.entities.CategoryType;
import com.softuni.coffeeshop.models.entities.Order;
import com.softuni.coffeeshop.models.entities.User;
import com.softuni.coffeeshop.repositories.CategoryRepository;
import com.softuni.coffeeshop.repositories.OrderRepository;
import com.softuni.coffeeshop.repositories.UserRepository;
import com.softuni.coffeeshop.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private OrderRepository orderRepository;
    private LoggedUser loggedUser;

    @Autowired
    public OrderService(UserRepository userRepository, CategoryRepository categoryRepository, OrderRepository orderRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
        this.loggedUser = loggedUser;
    }

    public boolean registerOrder(AddOrderDto addOrderDto) {

        Optional<User> employee = this.userRepository.findById(loggedUser.getId());

        CategoryType categoryType = CategoryType.valueOf(addOrderDto.getCategory().toUpperCase());
        Category category = this.categoryRepository.findByName(categoryType);

        Order order = new Order();
        order.setName(addOrderDto.getName());
        order.setPrice(addOrderDto.getPrice());
        order.setOrderTime(addOrderDto.getDateTime());
        order.setCategory(category);
        order.setDescription(addOrderDto.getDescription());
        order.setEmployee(employee.get());

        this.orderRepository.save(order);

        return true;
    }

    public List<Order> getOrders() {

        List<Order> allByCategory = this.orderRepository.findAllByOrderByPriceDesc();
        return allByCategory;

    }

    public List<User> getUsersWithOrders() {
        return this.getOrders().stream().map(Order::getEmployee).collect(Collectors.toList());
    }
}

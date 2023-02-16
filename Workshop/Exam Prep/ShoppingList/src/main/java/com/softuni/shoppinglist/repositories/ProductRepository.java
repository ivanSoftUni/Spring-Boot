package com.softuni.shoppinglist.repositories;

import com.softuni.shoppinglist.models.entities.Category;
import com.softuni.shoppinglist.models.entities.CategoryType;
import com.softuni.shoppinglist.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);


    Set<Product> findAllByCategory(Category category);
}

package com.softuni.shoppinglist.repositories;

import com.softuni.shoppinglist.models.entities.Category;
import com.softuni.shoppinglist.models.entities.CategoryType;
import com.softuni.shoppinglist.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(CategoryType category);

}

package com.softuni.coffeeshop.repositories;

import com.softuni.coffeeshop.models.entities.Category;
import com.softuni.coffeeshop.models.entities.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Category findByName(CategoryType categoryType);
}

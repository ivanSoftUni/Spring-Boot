package com.softuni.battle_ships.repositories;

import com.softuni.battle_ships.models.entities.Category;
import com.softuni.battle_ships.models.entities.Ship;
import com.softuni.battle_ships.models.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(CategoryName name);
}

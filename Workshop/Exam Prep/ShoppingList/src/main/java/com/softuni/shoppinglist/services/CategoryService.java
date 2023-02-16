package com.softuni.shoppinglist.services;

import com.softuni.shoppinglist.models.entities.Category;
import com.softuni.shoppinglist.models.entities.CategoryType;
import com.softuni.shoppinglist.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category getCategory(String name) {
        return this.categoryRepository.findByName(CategoryType.valueOf(name));
    }
}

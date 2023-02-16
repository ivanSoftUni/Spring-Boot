package com.softuni.shoppinglist.init;

import com.softuni.shoppinglist.models.entities.Category;
import com.softuni.shoppinglist.models.entities.CategoryType;
import com.softuni.shoppinglist.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoySeeder implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.categoryRepository.count() == 0) {
            List<Category> categoryList = Arrays.stream(CategoryType.values())
                    .map(Category::new)
                    .collect(Collectors.toList());

            this.categoryRepository.saveAll(categoryList);
        }

    }
}

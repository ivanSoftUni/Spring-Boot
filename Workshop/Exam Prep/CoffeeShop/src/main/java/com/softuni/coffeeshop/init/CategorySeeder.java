package com.softuni.coffeeshop.init;

import com.softuni.coffeeshop.models.entities.Category;
import com.softuni.coffeeshop.models.entities.CategoryType;
import com.softuni.coffeeshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategorySeeder implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.categoryRepository.count() == 0) {
            List<Category> categoryList = Arrays.stream(CategoryType.values())
                    .map(Category::new).toList();
            for (Category category : categoryList) {
                switch (category.getName()){
                    case COFFEE -> category.setNeedTime(2);
                    case DRINK -> category.setNeedTime(1);
                    case CAKE -> category.setNeedTime(5);
                    case OTHER -> category.setNeedTime(10);
                }
            }

            this.categoryRepository.saveAll(categoryList);

        }


    }
}

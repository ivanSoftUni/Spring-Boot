package com.softuni.shoppinglist.services;

import com.softuni.shoppinglist.models.dtos.AddProductDto;
import com.softuni.shoppinglist.models.entities.Category;
import com.softuni.shoppinglist.models.entities.Product;
import com.softuni.shoppinglist.repositories.CategoryRepository;
import com.softuni.shoppinglist.repositories.ProductRepository;
import com.softuni.shoppinglist.repositories.UserRepository;
import com.softuni.shoppinglist.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private LoggedUser loggedUser;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository, LoggedUser loggedUser) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public boolean registerProduct(AddProductDto addProductDto) {

        Category category = this.categoryRepository.findByName(addProductDto.getCategory());

        Optional<Product> byName = this.productRepository.findByName(addProductDto.getName());
        if (byName.isPresent()) {
            return false;
        }
        Product product = new Product();
        product.setName(addProductDto.getName());
        product.setDescription(addProductDto.getDescription());
        product.setPrice(addProductDto.getPrice());
        product.setBefore(addProductDto.getBefore());
        product.setCategory(category);

        this.productRepository.save(product);
        return true;
    }

}

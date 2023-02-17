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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private LoggedUser loggedUser;
    private CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository, LoggedUser loggedUser, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.categoryService = categoryService;
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
        product.setNeedBefore(addProductDto.getBefore());
        product.setCategory(category);

        this.productRepository.save(product);
        return true;
    }

    public Set<Product> getProducts(String name) {
        Category category = this.categoryService.getCategory(name);
        return this.productRepository.findAllByCategory(category);
    }


    public void buyProduct(Long id) {

        this.productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {

        return this.productRepository.findAll();
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = this.getAllProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return totalPrice;
    }

    public void removeAllProducts() {
        this.productRepository.deleteAll();
    }
}

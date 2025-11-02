package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Product getById(Long id) {
        return service.getById(id);
    }
    @PostMapping ("/create")
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }
    @DeleteMapping("/{id}")
    public void delete(Long id) {
        service.delete(id);
    }
}

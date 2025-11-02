package com.ecommerce.productservice.service;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductEventProducer producer;

    public ProductService(ProductRepository repository, ProductEventProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product save(Product product) {
        Product savedProduct = repository.save(product);
        producer.sendProductEvent("New product added: " + savedProduct.getName());
        return savedProduct;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

package com.estelle.store.product.domain.service;

import com.estelle.store.product.domain.model.Product;
import com.estelle.store.product.domain.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    public List<Product> getProducts(){
        return repo.findAll();
    }

    public Product getProduct(int prodId){
        return repo.findById(prodId).orElse(new Product());
    }

    public void createProduct(Product prod){
        repo.save(prod);
    }

    public void updateProduct(Product prod) {
        repo.save(prod);
    }

    public void deleteProduct(int prodId) {
        repo.deleteById(prodId);
    }
}

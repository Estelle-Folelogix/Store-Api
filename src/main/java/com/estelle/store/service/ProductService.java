package com.estelle.store.service;

import com.estelle.store.model.Product;
import com.estelle.store.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

//    List<Product> products = new ArrayList<>(Arrays.asList(   //mutable array
//            new Product(0,"IPhone", 90000),
//            new Product(1,"Smart Watch", 15000),
//            new Product(2, "Charger", 5000)));

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

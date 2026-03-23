package com.estelle.store.controller;

import com.estelle.store.model.Product;
import com.estelle.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping("/product")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/product/{prodId}")
    public Product getProduct(@PathVariable int prodId){
        return service.getProduct(prodId);
    }

    @PostMapping("/product")
    public void createProduct(@RequestBody Product prod){
        service.createProduct(prod);
    }

    @PutMapping("/product")
    public void updateProduct(@RequestBody Product prod){
        service.updateProduct(prod);
    }

    @DeleteMapping("/product/{prodId}")
    public void deleteProduct(@PathVariable int prodId){
        service.deleteProduct(prodId);
    }
}

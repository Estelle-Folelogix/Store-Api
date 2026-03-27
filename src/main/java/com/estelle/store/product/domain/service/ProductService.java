package com.estelle.store.product.domain.service;

import com.estelle.store.product.domain.model.Product;
import com.estelle.store.product.domain.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    ProductRepo repo;

    public List<Product> getProducts(){
        return repo.findAll();
    }

    public Product getProduct(int prodId){
        return repo.findById(prodId).orElse(new Product());
    }

    public Product createProduct(Product prod, MultipartFile imageFile) throws IOException {
        prod.setImageName(imageFile.getOriginalFilename());
        prod.setImageType(imageFile.getContentType());
        prod.setImageData(imageFile.getBytes());
        return repo.save(prod);
    }

    public Product updateProduct(Integer prodId, Product prod, MultipartFile imageFile) throws IOException {
        prod.setProId(prodId);
        prod.setImageData(imageFile.getBytes());
        prod.setImageName(imageFile.getOriginalFilename());
        prod.setImageType(imageFile.getContentType());
        return repo.save(prod);
    }

    public void deleteProduct(int prodId) {
        repo.deleteById(prodId);
    }

    public List<Product> searchProducts(String keyword) {
       return repo.searchProducts(keyword);
    }
}

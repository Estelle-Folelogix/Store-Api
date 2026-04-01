package com.estelle.store.product.presentation;

import com.estelle.store.exception.ResourceNotFoundException;
import com.estelle.store.product.domain.model.Category;
import com.estelle.store.product.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories(){
        return new ResponseEntity<>(service.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer categoryId) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getCategory(categoryId), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
       return new ResponseEntity<>(service.createCategory(category), HttpStatus.OK);
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer categoryId,
                                                   @RequestBody Category category) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.updateCategory(categoryId, category), HttpStatus.OK);
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId) throws ResourceNotFoundException {
        service.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

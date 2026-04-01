package com.estelle.store.product.domain.service;

import com.estelle.store.exception.ResourceNotFoundException;
import com.estelle.store.product.domain.model.Category;
import com.estelle.store.product.domain.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo repo;

    public List<Category> getCategories(){
        return repo.findAll();
    }

    public Category getCategory(Integer categoryId) throws ResourceNotFoundException {
        return repo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    public Category createCategory(Category category){
        return repo.save(category);
    }

    public Category updateCategory(Integer categoryId, Category category) throws ResourceNotFoundException {
        Category existing = repo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (category.getName() != null) {
            existing.setName(category.getName());
        }

        return repo.save(existing);
    }

    public void deleteCategory(Integer categoryId) throws ResourceNotFoundException {
        Category category = repo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        repo.delete(category);
    }
}

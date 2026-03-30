package com.estelle.store.product.domain.service;

import com.estelle.store.product.domain.model.Users;
import com.estelle.store.product.domain.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public Users register(Users user){
        return repo.save(user);
    }
}

package com.estelle.store.product.domain.service;

import com.estelle.store.product.domain.model.UserPrincipal;
import com.estelle.store.product.domain.model.Users;
import com.estelle.store.product.domain.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = repo.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        //create a class that implements userDetails(UserPrincipal)
        return new UserPrincipal(user);
    }
}

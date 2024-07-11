package com.project.zosale.service;

import com.project.zosale.entity.User;
import com.project.zosale.repository.UserRepository;
import com.project.zosale.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return JwtUserDetails.create(user);
    }
    public UserDetails loadUserById(Long id ){
        User user = userRepository.findById(id).get();


        return JwtUserDetails.create(user);
    }
}

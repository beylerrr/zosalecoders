package com.project.zosale.service;

import com.project.zosale.entity.User;
import com.project.zosale.entity.UserShop;
import com.project.zosale.repository.UserRepository;
import com.project.zosale.repository.UserShopRepository;
import com.project.zosale.response.UserShopResponse;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserShopService {

    @Autowired
    private UserShopRepository userShopRepository;

    @Autowired
    private UserRepository userRepository;


    public UserShop createUserShop(UserShop userShop) {
        UserShop userShopToUser = userShopRepository.save(userShop);
        return userShopToUser;

    }

    public UserShopResponse getUserShopByUser(Authentication authentication){
        UserDetails userDetails =(UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());

        UserShopResponse userShopResponse = new UserShopResponse(user.getUserShop());

        return userShopResponse;





    }


}

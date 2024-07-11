package com.project.zosale.controller;

import com.project.zosale.entity.User;
import com.project.zosale.entity.UserShop;

import com.project.zosale.request.UserRequest;
import com.project.zosale.response.CustomerResponse;
import com.project.zosale.response.UserResponse;
import com.project.zosale.response.UserShopResponse;
import com.project.zosale.service.CustomerService;
import com.project.zosale.service.UserService;
import com.project.zosale.service.UserShopService;
import javassist.NotFoundException;
import org.apache.coyote.Response;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserShopService userShopService;




    @GetMapping("/getall")
    public List<UserResponse> getAll(@RequestParam Optional<Long> id){



        return userService.getAllUsers(id);

    }

    @PostMapping("/add")
    public void addUser(@RequestBody UserRequest userRequest){
         userService.addUser(userRequest);
    }

    @PutMapping("/update/{id}")
    public User updateOneUser(@PathVariable Long id,@RequestBody User user){
        return userService.updateOneUser(id,user);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Long id){
        return userService.deleteUser(id);

    }
    @GetMapping("/user-shop")
    public UserShopResponse getUserShopById(Authentication authentication)  {
        return userShopService.getUserShopByUser(authentication);


    }
//    @PostMapping("/message")
//    public ResponseEntity<String> sendMessage(@RequestBody MessageRequest messageRequest, Authentication authentication){
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String sender = userDetails.getUsername();
//
//
//        return new ResponseEntity<String>(userService.sendMessageToOtherUser(sender,messageRequest.getReciever(), messageRequest.getMessage()),HttpStatus.OK);
//    }




}

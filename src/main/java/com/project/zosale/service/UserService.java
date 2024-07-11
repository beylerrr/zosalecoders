package com.project.zosale.service;

import com.project.zosale.entity.User;
import com.project.zosale.repository.UserRepository;
import com.project.zosale.request.UserRequest;
import com.project.zosale.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository ;
    public List<UserResponse> getAllUsers(Optional<Long> id){
        List<User> list ;
        if(id.isPresent()){
            list = userRepository.getUserById(id);
        }else{
            list = userRepository.findAll();
        }
        return list.stream().map(user -> new UserResponse(user)).collect(Collectors.toList());

    }
    public User updateOneUser(Long id, User user){
        Optional<User> userById = userRepository.findById(id);
        if(userById.isPresent()){
            User foundUser = userById.get();
            foundUser.setEmail(user.getEmail());
            foundUser.setPassword(passwordEncoder.encode(user.getPassword()));
            foundUser.setAddress(user.getAddress());
            foundUser.setCity(user.getCity());
            foundUser.setCountry(user.getCountry());
            foundUser.setPostalCode(user.getPostalCode());
            foundUser.setPhoneNumber(user.getPhoneNumber());


            userRepository.save(foundUser);
            return foundUser;




        }
        throw new IllegalStateException("These fields cannot be null");

    }

    public List<User> getOneUserById(Optional<Long> id ){

        return userRepository.getUserById(id);
    }

    public void addUser(UserRequest userRequest){
        User user = new User();
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurName());


        userRepository.save(user);
    }
    public ResponseEntity<String> deleteUser(Long id ){
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            User userToDelete =user.get();
            userRepository.delete(userToDelete);
            return new ResponseEntity<>("User deleted", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public User getOneUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void saveOneUser(User user){
        userRepository.save(user);
    }

    public User getOneUserById(Long id ){
        return userRepository.getUserById(id);
    }

//    public String sendMessageToOtherUser(String sender,String reciever,String message) {
//        return "Sender: "+sender+"\n"+"Reciever: "+reciever+"\n"+"Message:"+message;
//
//    }
}






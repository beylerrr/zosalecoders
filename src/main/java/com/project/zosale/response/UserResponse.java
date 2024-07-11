package com.project.zosale.response;

import com.project.zosale.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    private Long id ;
    private String email;
    private String password ;
    private String name;
    private String surname;
    private String phoneNumber;
    private String country;
    private String city;
    private String address;
    private int postalCode;
    private Long  imageId;

    public UserResponse(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phoneNumber = user.getPhoneNumber();
        this.country = user.getCountry();
        this.city =user.getCity();
        this.address = user.getAddress();
        this.postalCode = user.getPostalCode();
        if(user.getImage() ==null){
            this.imageId = 0L;}else{
        this.imageId = user.getImage().getId();}


    }



}

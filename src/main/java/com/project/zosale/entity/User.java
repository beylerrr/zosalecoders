package com.project.zosale.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "app_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public User(String name,String surname,String email,String password){
        this.name = name;
        this.surname = surname ;
        this.email = email ;
        this.password = password;

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @NonNull
    private String email;
    @NonNull
    private String password ;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    private String phoneNumber;
    private String country;
    private String city;
    private String address;
    private int postalCode;


    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Image image ;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    private UserShop userShop;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Product> products;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Customer> customers;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    @JsonIgnore
    private List<Order> order;



}

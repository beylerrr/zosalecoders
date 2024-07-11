package com.project.zosale.request;

import com.project.zosale.entity.User;
import com.project.zosale.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String email;
    private String password ;
    private String name;
    private String surName;


}

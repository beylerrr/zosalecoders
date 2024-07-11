package com.project.zosale.repository;

import com.project.zosale.entity.User;
import com.project.zosale.entity.UserShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserShopRepository extends JpaRepository<UserShop, Long> {

    public UserShop getUserShopByUserId(Long userId);






}

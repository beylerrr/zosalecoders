package com.project.zosale.repository;

import com.project.zosale.entity.User;
import com.project.zosale.entity.UserShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public User getUserByUserShop(UserShop userShop);

    public List<User> getUserById(Optional<Long> id);
    public User findByEmail(String email);

    public User getUserById(Long id );





}

package com.project.zosale.repository;

import com.project.zosale.entity.Product;
import com.project.zosale.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

     public List<Product> getProductByProductId(Optional<Long> id );



     public Product getProductByProductId(Long id);

     public List<Product> getProductByUser(User user);

}

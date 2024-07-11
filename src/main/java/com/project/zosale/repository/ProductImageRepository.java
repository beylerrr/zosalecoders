package com.project.zosale.repository;

import com.project.zosale.entity.Product;
import com.project.zosale.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {


    public Optional<ProductImage> findProductImageByProductId(Long id);
    public ProductImage findProductImageById(Long id);


}

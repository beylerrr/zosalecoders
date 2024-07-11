package com.project.zosale.service;

import com.project.zosale.entity.Product;
import com.project.zosale.entity.ProductImage;
import com.project.zosale.repository.ProductImageRepository;
import com.project.zosale.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public class ProductImageService {
    @Autowired
    private ProductImageRepository imageRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductImage addImage(MultipartFile file,Long id) throws IOException {



            Product product = productRepository.getProductByProductId(id);
            ProductImage productImage = new ProductImage();
            productImage.setFileData(file.getBytes());
            productImage.setName(file.getOriginalFilename());
            productImage.setContentType(file.getContentType());
            productImage.setProductId(product.getProductId());

            return productImage;

    }

    public ResponseEntity<?> getFileById(Long id) throws FileNotFoundException {
        ProductImage productImage = productImageRepository.findProductImageById(id);
        if(productImage !=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Type",productImage.getContentType())
                    .body(productImage.getFileData());
        }
        throw new FileNotFoundException("File Not Found");
    }






}

package com.project.zosale.service;

import com.project.zosale.entity.Product;
import com.project.zosale.entity.ProductImage;
import com.project.zosale.entity.User;
import com.project.zosale.repository.ProductImageRepository;
import com.project.zosale.repository.ProductRepository;
import com.project.zosale.repository.UserRepository;
import com.project.zosale.request.ProductRequest;
import com.project.zosale.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductImageService productImageService;

    public List<ProductResponse> getAllProducts(Optional<Long> id){
        List<Product> list;
        if(id.isPresent()){
            list = productRepository.getProductByProductId(id);
        }else{
            list = productRepository.findAll();
        }
        return list.stream().map(product -> new ProductResponse(product)).collect(Collectors.toList());

    }

    public void addProduct(ProductRequest request, MultipartFile file, Authentication authentication) throws IOException {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByEmail(userDetails.getUsername());
            Product toSave = new Product();
            toSave.setProductName(request.getProductName());
            toSave.setPrice(request.getPrice());
            toSave.setStock(request.getStock());
            toSave.setUser(user);
            productRepository.save(toSave);
            ProductImage productImage = productImageService.addImage(file,toSave.getProductId());
            toSave.setImage(productImage);
            productImage.setProductId(toSave.getProductId());
            productImageRepository.save(productImage);




    }
}

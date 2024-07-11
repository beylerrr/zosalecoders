package com.project.zosale.controller;

import com.project.zosale.request.ProductRequest;
import com.project.zosale.response.ProductResponse;
import com.project.zosale.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getall")
    public List<ProductResponse> getAll(@RequestParam Optional<Long> id) {
        return productService.getAllProducts(id);
    }

    @PostMapping(value = "/add-product", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> saveProduct(@RequestPart(value = "file") MultipartFile file,
                                         @RequestPart(value = "product") ProductRequest product, Authentication auth) throws IOException {
        try{
        productService.addProduct(product, file, auth);
        return new ResponseEntity<String>("Product Created Successfully", HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity<String>("Something Went Wrong",HttpStatus.BAD_REQUEST);
       }


    }
}

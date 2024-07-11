package com.project.zosale.response;

import com.project.zosale.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class ProductResponse {
    private long id ;
    private BigDecimal price;
    private int stock ;
    private String name;
    private long imageId;
    private long userId;


    public ProductResponse(Product product) {
        this.id = product.getProductId();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.name = product.getProductName();
        this.userId = product.getUser().getId();
        if (product.getImage() == null) {
            this.imageId = 0L;
        } else {
            this.imageId = product.getImage().getId();
        }
    }
}

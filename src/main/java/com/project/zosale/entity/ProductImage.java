package com.project.zosale.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@Table(name = "app_pr_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name ;

    private String contentType;

    @Lob
    private byte[] fileData;

    @JoinColumn(name = "product_id")
    private Long productId;

}

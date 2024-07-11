package com.project.zosale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "app_customer_img")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name ;
    private String contentType ;

    @Lob
    private byte[] fileData;

    @JoinColumn(name = "customer_id")
    private Long customerId;
}

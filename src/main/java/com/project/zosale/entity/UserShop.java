package com.project.zosale.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "user_shop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserShop {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal netIncome= BigDecimal.ZERO;
    private int totalOrders;
    private BigDecimal averageSales = BigDecimal.ZERO;
    private BigDecimal impressions = BigDecimal.ZERO;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id")
    private Long userId;

}

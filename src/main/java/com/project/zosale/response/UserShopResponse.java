package com.project.zosale.response;

import com.project.zosale.entity.UserShop;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class UserShopResponse {
    private  long id ;
    private BigDecimal netIncome=BigDecimal.ZERO;
    private int totalOrders=0;
    private BigDecimal averageSales = BigDecimal.ZERO;
    private BigDecimal impressions = BigDecimal.ZERO;

    public UserShopResponse (UserShop userShop){
        this.id = userShop.getUserId();
        this.netIncome = userShop.getNetIncome();
        this.totalOrders =userShop.getTotalOrders();
        this.averageSales = userShop.getAverageSales();
        this.impressions = userShop.getImpressions();

    }



}

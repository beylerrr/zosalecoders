package com.project.zosale.response;

import com.project.zosale.entity.Order;
import com.project.zosale.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private UUID orderId;
    private Long userId;
    private Long customerId;
    private Long productId;
    private BigDecimal orderAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Date creationDate;

    public OrderResponse(Order order){
        this.orderId = order.getOrderId();
        this.userId = order.getUser().getId();
        this.customerId = order.getCustomer().getId();
        this.productId = order.getProduct().getProductId();
        this.orderAmount = order.getOrderAmount();
        this.orderStatus = order.getOrderStatus();
        this.creationDate = order.getCreatedAt();
    }

}

package com.project.zosale.controller;

import com.project.zosale.entity.Order;
import com.project.zosale.request.OrderRequest;
import com.project.zosale.response.CustomerResponse;
import com.project.zosale.response.OrderResponse;
import com.project.zosale.service.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/post-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest, Authentication authentication) throws Exception {

            try {
            orderService.createOder(orderRequest,authentication);
            return new ResponseEntity<String>("Order created successfully",HttpStatus.CREATED);
            } catch (Exception e) {

                return new ResponseEntity<String>("Something went wrong while creating order",HttpStatus.BAD_REQUEST);
            }
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<OrderResponse>> getOrdersOfUser(@RequestParam Optional<UUID> id, Authentication authentication) throws Exception {
        try {

            return new ResponseEntity<List<OrderResponse>>(orderService.getOrdersWithUser(id, authentication),
                    HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Something went wrong");
        }

    }



}

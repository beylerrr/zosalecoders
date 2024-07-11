package com.project.zosale.service;

import com.project.zosale.entity.*;
import com.project.zosale.repository.*;
import com.project.zosale.request.OrderRequest;
import com.project.zosale.response.OrderResponse;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserShopRepository userShopRepository;

    public void createOder(OrderRequest request, Authentication authentication) throws Exception {

        if(checkCustomerExistsByUser(request.getCustomerId(),authentication) &&
                checkProductExistsByUser(request.getProductId(),authentication)){
            UserDetails user = (UserDetails) authentication.getPrincipal();
            Order order = new Order();

            order.setUser(userRepository.findByEmail(user.getUsername()));
            order.setProduct(productRepository.getById(request.getProductId()));
            order.setCustomer(customerRepository.getCustomerById(request.getCustomerId()));
            order.setOrderAmount(productRepository.getById(request.getProductId()).getPrice());
            orderRepository.save(order);
            updateUserShop(authentication,order);



        }
        else{
          throw new Exception("Something went wrong");
         }
    }


    public boolean checkProductExistsByUser(Long productId,Authentication authentication) throws NotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());

        List<Product> userProducts = productRepository.getProductByUser(user);

        for(Product pr : userProducts){
            if(pr.getProductId() == productId){
                return true;
            }
        }
        throw new NotFoundException("This product doesn't exists");
    }

    public boolean checkCustomerExistsByUser(Long customerId,Authentication authentication) throws NotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());;
        List<Customer> userCustomers = customerRepository.getCustomerByUser(user);

        for(Customer c : userCustomers){
            if(c.getId() == customerId){
                return true;
            }
        }
        throw new NotFoundException("This Customer Not found");
    }


    public List<OrderResponse> getOrdersWithUser(Optional<UUID> orderId,Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());
        List<Order> list = new ArrayList<>();

        if(orderId.isPresent()){
            list = orderRepository.getOrderByOrderId(orderId);
        }else{
            list = orderRepository.getOrderByUser(user);
        }

        return list.stream().map(order -> new OrderResponse(order)).collect(Collectors.toList());
    }


    public void updateUserShop(Authentication authentication,Order order){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());
        UserShop userShop = user.getUserShop();

        userShop.setTotalOrders(userShop.getTotalOrders()+1);

        userShop.setNetIncome(userShop.getNetIncome().add(order.getProduct().getPrice()));
        userShop.setImpressions(userShop.getImpressions().add(order.getProduct().getPrice()));
        BigDecimal divisor = new BigDecimal(userShop.getTotalOrders());

        userShop.setAverageSales(userShop.getNetIncome().divide(divisor,2, RoundingMode.HALF_UP));

        userShopRepository.save(userShop);
    }

}

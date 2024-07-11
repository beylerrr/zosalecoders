package com.project.zosale.repository;

import com.project.zosale.entity.Order;
import com.project.zosale.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    public List<Order> getOrderByOrderId(Optional<UUID> uuid);

    public List<Order> getOrderByUser(User user);
}

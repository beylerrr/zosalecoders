package com.project.zosale.repository;

import com.project.zosale.entity.Customer;
import com.project.zosale.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    public Customer getCustomerById(Long customerId);
    public List<Customer> getCustomerById(Optional<Long> id);

    public Customer getCustomerByEmail(String email);

    public List<Customer> getCustomerByUser(User user);
//    public Customer getOneCustomerOptional(Optional<Long> id);




}

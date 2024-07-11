package com.project.zosale.repository;

import com.project.zosale.entity.CustomerImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public interface CustomerImageRepository extends JpaRepository<CustomerImage,Long> {

    public CustomerImage findCustomerImageById(Long id);
}

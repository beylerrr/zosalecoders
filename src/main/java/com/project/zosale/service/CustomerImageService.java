package com.project.zosale.service;

import com.project.zosale.entity.Customer;
import com.project.zosale.entity.CustomerImage;
import com.project.zosale.repository.CustomerImageRepository;
import com.project.zosale.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class CustomerImageService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerImageRepository customerImageRepository;

    public CustomerImage addImageToCustomer(MultipartFile file,long customerId) throws IOException {

        Customer customer = customerRepository.getCustomerById(customerId);

        CustomerImage customerImage = new CustomerImage();
        customerImage.setName(file.getOriginalFilename());
        customerImage.setContentType(file.getContentType());
        customerImage.setFileData(file.getBytes());
        customerImage.setCustomerId(customer.getId());


        return customerImage;
    }

    public ResponseEntity<?> getFileById(Long id) throws FileNotFoundException {
        CustomerImage customerImage = customerImageRepository.findCustomerImageById(id);

        if(customerImage != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Type",customerImage.getContentType())
                    .body(customerImage.getFileData());
        }
        throw new FileNotFoundException("file not found");
    }
}

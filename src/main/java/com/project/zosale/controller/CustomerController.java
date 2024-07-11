package com.project.zosale.controller;

import com.project.zosale.entity.Customer;
import com.project.zosale.repository.CustomerRepository;
import com.project.zosale.request.CustomerRequest;
import com.project.zosale.response.CustomerResponse;
import com.project.zosale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(value = "/create",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createCustomer(@RequestPart(value = "file") MultipartFile file,
                                            @RequestPart(value = "customer") CustomerRequest customerRequest,
                                                    Authentication authentication) throws IOException {

        if(customerRepository.getCustomerByEmail(customerRequest.getEmail()) !=null){
            return new ResponseEntity<>("Customer is already exists", HttpStatus.BAD_REQUEST);
        }
        customerService.addCustomer(customerRequest,file,authentication);
        return new ResponseEntity<>("Customer created successfullly",HttpStatus.CREATED);
    }
    @GetMapping("/getall-customer")
    public List<CustomerResponse> getAll(Optional<Long> id){
        return customerService.getAllCustomers(id);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CustomerResponse>> getCustomerByUser(Authentication authentication,
                                                                    @PathVariable("id") Optional<Long> customerId) throws Exception {

        return new ResponseEntity<List<CustomerResponse>>(customerService.getCustomerByUser(authentication,customerId),
                HttpStatus.OK);
    }




}

package com.project.zosale.service;

import com.project.zosale.entity.Customer;
import com.project.zosale.entity.CustomerImage;
import com.project.zosale.entity.User;
import com.project.zosale.repository.CustomerImageRepository;
import com.project.zosale.repository.CustomerRepository;
import com.project.zosale.repository.UserRepository;
import com.project.zosale.request.CustomerRequest;
import com.project.zosale.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerImageService imageService;
    @Autowired
    private CustomerImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;

    public void addCustomer(CustomerRequest request, MultipartFile file, Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());
        Customer customerToSave = new Customer();
        customerToSave.setFirstName(request.getFirstName());
        customerToSave.setLastName(request.getLastName());
        customerToSave.setEmail(request.getEmail());
        customerToSave.setAddress(request.getAddress());
        customerToSave.setPhoneNumber(request.getPhoneNumber());
        customerToSave.setTags(request.getTags());
        customerToSave.setNotes(request.getNotes());
        customerToSave.setUser(user);
        customerRepository.save(customerToSave);
        CustomerImage image = imageService.addImageToCustomer(file, customerToSave.getId());
        customerToSave.setImage(image);

        image.setCustomerId(customerToSave.getId());
        imageRepository.save(image);

    }


    public List<CustomerResponse> getAllCustomers(Optional<Long> id) {
        List<Customer> list;

        if(id.isPresent()){
            list = customerRepository.getCustomerById(id);
        }else{
            list =customerRepository.findAll();}
        return list.stream().map(customer -> new CustomerResponse(customer)).collect(Collectors.toList());
    }

    public List<CustomerResponse> getCustomerByUser(Authentication authentication,Optional<Long> customerId) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername());

        List<Customer> list = new ArrayList<Customer>();

        if(customerId.isPresent()){
            List<Customer> customers = customerRepository.getCustomerById(customerId);

            for(Customer c:customers){
                if(c.getUser() ==null && c.getUser() !=user){

                    throw new Exception("Cannot found customer with this id");
                }else{
                    list=customerRepository.getCustomerById(customerId);
                }
            }

        }else{
            list = customerRepository.getCustomerByUser(user);
        }
        return list.stream().map(customer -> new CustomerResponse(customer)).collect(Collectors.toList());
    }


}

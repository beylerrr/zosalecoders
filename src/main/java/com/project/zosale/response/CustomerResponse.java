package com.project.zosale.response;

import com.project.zosale.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String notes;
    private String tags;
    private String address;
    private Long imageId;
    private Long userId;

    public CustomerResponse(Customer customer){
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName=customer.getLastName();
        this.email=customer.getEmail();
        this.phoneNumber=customer.getPhoneNumber();
        this.notes=customer.getNotes();
        this.tags=customer.getTags();
        this.address=customer.getAddress();
        if(customer.getImage()==null){
            this.imageId=0L;
        }else{
        this.imageId = customer.getImage().getId();}
        this.userId = customer.getUser().getId();
    }

}

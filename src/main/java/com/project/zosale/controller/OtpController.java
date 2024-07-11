package com.project.zosale.controller;

import com.project.zosale.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class OtpController {
    @Autowired
    private OtpService otpService;

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email){
        otpService.createAndSendOtp(email);
        return "Otp send to "+email;
    }

    @PostMapping("/validate-otp")
    public boolean  validateOtp(@RequestParam String email,@RequestParam String otp ){
        return otpService.validateOtp(email,otp);
    }


}

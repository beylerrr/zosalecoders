package com.project.zosale.service;

import com.project.zosale.entity.Otp;
import com.project.zosale.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private OtpRepository otpRepository;

    private SecureRandom random = new SecureRandom();

    public String generateOtp(){
        int otp = 10000 + random.nextInt(90000);
        return String.valueOf(otp);
    }

    public void sendOtpMail(String to,String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your Otp Code");
        message.setText("Your Otp code is "+otp);
        javaMailSender.send(message);

    }

    public void createAndSendOtp(String email){
        String otp = generateOtp();
        Otp otpEntity = new Otp();
        otpEntity.setEmail(email);
        otpEntity.setCode(otp);
        otpEntity.setExpiryDate(LocalDateTime.now().plusMinutes(5));
        otpRepository.save(otpEntity);
        sendOtpMail(email,otp);
    }
    public boolean validateOtp(String email,String code){
        Optional<Otp> otp = otpRepository.findOtpByEmailAndCode(email,code);


        return otp.isPresent() && otp.get().getExpiryDate().isAfter(LocalDateTime.now());
    }



}

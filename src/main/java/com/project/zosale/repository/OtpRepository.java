package com.project.zosale.repository;

import com.project.zosale.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp,Long> {

    Optional<Otp> findOtpByEmailAndCode(String email,String code);
}

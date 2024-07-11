package com.project.zosale.controller;

import com.project.zosale.entity.User;
import com.project.zosale.entity.UserShop;
import com.project.zosale.repository.UserShopRepository;
import com.project.zosale.request.UserLoginRequest;
import com.project.zosale.request.UserRegisterRequest;
import com.project.zosale.security.JwtTokenProvider;
import com.project.zosale.service.OtpService;
import com.project.zosale.service.UserService;
import com.project.zosale.service.UserShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider ;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final UserShopService userShopService;
    private final UserShopRepository userShopRepository;

    public AuthController(UserService userService, AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder,UserShopService userShopService,UserShopRepository userShopRepository,OtpService otpService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userShopService = userShopService ;
        this.userShopRepository = userShopRepository;
        this.otpService = otpService;
    }


    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
        Authentication  auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return "Bearer "+jwtToken;

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userService.getOneUserByEmail(userRegisterRequest.getEmail()) != null){
            return new ResponseEntity<>("User is already exists", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(userRegisterRequest.getName());
        user.setSurname(userRegisterRequest.getSurName());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        otpService.createAndSendOtp(userRegisterRequest.getEmail());

        UserShop  userShop = new UserShop();
        user.setUserShop(userShopService.createUserShop(userShop));
        userService.saveOneUser(user);
        userShop.setUserId(user.getId());
        userShopRepository.save(userShop);

        return new ResponseEntity<>("User successfully registered",HttpStatus.CREATED);


    }

}

package com.farm_to_door.farm2door_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm_to_door.farm2door_API.DTO.LoginRequestDTO;
import com.farm_to_door.farm2door_API.DTO.RegistrationDTO;
import com.farm_to_door.farm2door_API.Service.UserService;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService thUserService){
        this.userService = thUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationDTO registrationDTO){
        return userService.registerUser(registrationDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequestDTO){
        return userService.loginUser(loginRequestDTO);
    }
}

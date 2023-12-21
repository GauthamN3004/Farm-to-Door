package com.farm_to_door.farm2door_API.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.farm_to_door.farm2door_API.DTO.LoginRequestDTO;
import com.farm_to_door.farm2door_API.Entity.Users;
import com.farm_to_door.farm2door_API.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> registerUser(Users user){
        String username = user.getUsername();

        if(userRepository.findByUsername(username) != null){
            return ResponseEntity.badRequest().body("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    public ResponseEntity<?> loginUser(LoginRequestDTO loginRequestDTO){
        String username = loginRequestDTO.getUsername();
        Users user = userRepository.findByUsername(username);
        
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
        }

        String password = loginRequestDTO.getPassword();
        if(!passwordEncoder.matches(password, user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
        }
        
        return ResponseEntity.ok(user);
    }

}

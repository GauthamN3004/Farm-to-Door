package com.farm_to_door.farm2door_API.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.farm_to_door.farm2door_API.DTO.LoginRequestDTO;
import com.farm_to_door.farm2door_API.DTO.RegistrationDTO;
import com.farm_to_door.farm2door_API.Entity.Customer;
import com.farm_to_door.farm2door_API.Entity.Farmer;
import com.farm_to_door.farm2door_API.Entity.Users;
import com.farm_to_door.farm2door_API.Repository.CustomerRepository;
import com.farm_to_door.farm2door_API.Repository.FarmerRepository;
import com.farm_to_door.farm2door_API.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private FarmerRepository farmerRepository;
    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository theUserRepository, PasswordEncoder thPasswordEncoder, FarmerRepository theFarmerRepository, CustomerRepository theCustomerRepository){
        this.userRepository = theUserRepository;
        this.passwordEncoder = thPasswordEncoder;
        this.customerRepository = theCustomerRepository;
        this.farmerRepository = theFarmerRepository;
    }
    
    @Transactional
    public ResponseEntity<String> registerUser(RegistrationDTO registrationDTO){
        String username = registrationDTO.getUsername();

        if(userRepository.findByUsername(username) != null){
            return ResponseEntity.badRequest().body("Username already exists");
        }
        
        if(registrationDTO.getUserType().equals("FARMER")){
            Farmer farmer = new Farmer();
            farmer.setUsername(registrationDTO.getUsername());
            farmer.setFirstname(registrationDTO.getFirstname());
            farmer.setLastname(registrationDTO.getLastname());
            farmer.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
            farmer.setEmail(registrationDTO.getEmail());
            farmer.setAddress(registrationDTO.getAddress());
            farmer.setCity(registrationDTO.getCity());
            farmer.setPhno(registrationDTO.getPhno());
            farmer.setEnabled(true);
            try{
                farmerRepository.save(farmer);
            } catch (Exception e){
                return ResponseEntity.badRequest().body("Could not regiter user - " + e);
            }
        }
        else if(registrationDTO.getUserType().equals("CUSTOMER")){
            Customer customer = new Customer();
            customer.setUsername(registrationDTO.getUsername());
            customer.setFirstname(registrationDTO.getFirstname());
            customer.setLastname(registrationDTO.getLastname());
            customer.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
            customer.setEmail(registrationDTO.getEmail());
            customer.setAddress(registrationDTO.getAddress());
            customer.setCity(registrationDTO.getCity());
            customer.setPhno(registrationDTO.getPhno());
            customer.setEnabled(true);
            try{
                customerRepository.save(customer);
            } catch (Exception e){
                return ResponseEntity.badRequest().body("Could not regiter user - " + e);
            }
        }
        else{
            return ResponseEntity.badRequest().body("Unknown UserType");
        }

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

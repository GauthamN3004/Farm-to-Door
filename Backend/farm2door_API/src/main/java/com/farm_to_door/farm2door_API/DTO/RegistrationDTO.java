package com.farm_to_door.farm2door_API.DTO;

public class RegistrationDTO {

    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String userType;
    private String email;
    private String address;
    private String city;
    private String phno;

    // Constructors

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String firstname, String lastname, String password, String userType,
                           String email, String address, String city, String phno) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.userType = userType;
        this.email = email;
        this.address = address;
        this.city = city;
        this.phno = phno;
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}

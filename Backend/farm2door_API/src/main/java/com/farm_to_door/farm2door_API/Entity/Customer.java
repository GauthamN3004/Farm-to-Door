package com.farm_to_door.farm2door_API.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends UserBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    // @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    // private List<Order> orders;

    // Constructors
    public Customer() {
    }

    public Customer(String username, String firstname, String lastname, String password, String email,
                    String address, String city, String phno, boolean enabled) {
        super(username, firstname, lastname, password, email, address, city, phno, enabled);
    }

    // public List<Order> getOrders() {
    //     return orders;
    // }

    // public void setOrders(List<Order> orders) {
    //     this.orders = orders;
    // }

    public Long getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + getCustomerId() +
                ", username='" + getUsername() + '\'' +
                ", firstname='" + getFirstname() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", city='" + getCity() + '\'' +
                ", phno='" + getPhno() + '\'' +
                ", enabled=" + isEnabled() +
                // ", orders=" + orders +
                '}';
    }
}

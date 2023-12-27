package com.farm_to_door.farm2door_API.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "farmer")
public class Farmer extends UserBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "farmer_id")
    private Long farmerId;

    // @OneToMany(mappedBy = "farmer", fetch = FetchType.LAZY)
    // private List<Harvest> harvests;

    // Constructors
    public Farmer() {
    }

    public Farmer(String username, String firstname, String lastname, String password, String email,
                  String address, String city, String phno, boolean enabled) {
        super(username, firstname, lastname, password, email, address, city, phno, enabled);
    }

    
    // public List<Harvest> getHarvests() {
    //     return harvests;
    // }

    // public void setHarvests(List<Harvest> harvests) {
    //     this.harvests = harvests;
    // }

    public Long getFarmerId() {
        return farmerId;
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "farmerId=" + getFarmerId() +
                ", username='" + getUsername() + '\'' +
                ", firstname='" + getFirstname() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", city='" + getCity() + '\'' +
                ", phno='" + getPhno() + '\'' +
                ", enabled=" + isEnabled() +
                //", harvests=" + harvests +
                '}';
    }
}
package com.farm_to_door.farm2door_API.DAO;

import com.farm_to_door.farm2door_API.Entity.Users;

public interface UsersDAO {
    Users findById(Long userId);
    Users findByUsername(String username);
}

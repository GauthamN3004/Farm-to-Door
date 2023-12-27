package com.farm_to_door.farm2door_API.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import com.farm_to_door.farm2door_API.DAO.UsersDAO;
import com.farm_to_door.farm2door_API.Entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class UserRepository implements UsersDAO {

    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);


    public UserRepository(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public Users findById(Long user_id) {
        return entityManager.find(Users.class, user_id);
    }

    @Override
    public Users findByUsername(String username) {
        Query jpqlQuery = entityManager.createQuery("SELECT u FROM Users u WHERE u.username=:username");
        jpqlQuery.setParameter("username", username);
        try {
            return (Users) jpqlQuery.getSingleResult();
        } catch (Exception e) {
            logger.error("Error - " + e);
            return null;
        }
    }
}
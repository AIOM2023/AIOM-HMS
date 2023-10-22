package com.hospital.management.service;

import com.hospital.management.entities.User;

import java.util.List;


public interface UserService {

    //Create User and save
    User saveUser(User user);

    // Get All Users
    List<User> getAllUsers();

    //Get User Based on ID
    User getUser(String userId);


}

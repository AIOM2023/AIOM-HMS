package com.hospital.management.service.impl;

import com.hospital.management.entities.User;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.repositary.UserRepositary;
import com.hospital.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositary userRepositary;


    @Override
    public User saveUser(User user) {
        /*String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);*/
        return userRepositary.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositary.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepositary.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id not Found:"+ userId));
    }

}

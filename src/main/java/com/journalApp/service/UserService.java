package com.journalApp.service;

import com.journalApp.entity.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user);


    public void deleteUserByusername(String userName);

    public List<User> getAllUsers();

    public User getUserByusername(String userName);
}

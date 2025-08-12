package com.journalApp.service;

import com.journalApp.entity.User;
import com.journalApp.payload.UserDto;

import java.util.List;

public interface UserService {

    public UserDto saveUser(UserDto userDto);


    public void deleteUserByusername(String userName);

    public List<UserDto> getAllUsers();

    public UserDto getUserByusername(String userName);
}

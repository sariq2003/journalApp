package com.journalApp.controller;


import com.journalApp.entity.User;
import com.journalApp.repository.UserRespository;
import com.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserContoller {

    @Autowired
   private UserService userService;



    //http://localhost:8080/api/user/saveuser
    @PostMapping("/saveuser")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //http:/localhost:8080/api/user/getAllUsers
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    //http:/localhost:8080/api/user//getUserByUsername/{userName}
    @GetMapping("/getUserByUsername/{userName}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String userName){
        User user = userService.getUserByusername(userName);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //http://localhost:8080/api/user/deleteUser/{userName}
    @DeleteMapping("/deleteUser/{userName}")
    public ResponseEntity<?> saveUser(@PathVariable String userName) {
           userService.deleteUserByusername(userName);
        return new ResponseEntity<>("User Deleted", HttpStatus.NO_CONTENT);
    }


}

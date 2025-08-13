package com.journalApp.controller;


import com.journalApp.entity.User;
import com.journalApp.payload.UserDto;
import com.journalApp.repository.UserRespository;
import com.journalApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserContoller {

    @Autowired
   private UserService userService;



    //http://localhost:8080/api/user/saveuser
    @PostMapping("/saveuser")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDto userDto, BindingResult result) {

        if(result.hasErrors()){
            Map<String,String> error = new HashMap<>();
            result.getFieldErrors().forEach(fielderror->error.put(fielderror.getField(),fielderror.getDefaultMessage()));
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        UserDto savedUser = userService.saveUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/user/getAllUsers
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    //http:/localhost:8080/api/user//getUserByUsername/{userName}
    @GetMapping("/getUserByUsername/{userName}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String userName){
        UserDto userDto = userService.getUserByusername(userName);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    //http://localhost:8080/api/user/deleteUser/{userName}
    @DeleteMapping("/deleteUser/{userName}")
    public ResponseEntity<?> saveUser(@PathVariable String userName) {
           userService.deleteUserByusername(userName);
        return new ResponseEntity<>("User Deleted", HttpStatus.NO_CONTENT);
    }


}

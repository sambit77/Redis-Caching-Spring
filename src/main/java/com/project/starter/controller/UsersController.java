package com.project.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.project.starter.data.UserEntity;
import com.project.starter.exchange.AddUserRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersController {

    private final com.project.starter.service.UserService  userService;
    
    @PostMapping("/adduser")
    public ResponseEntity<UserEntity> addUser(@RequestBody AddUserRequest entity) {
        //TODO: process POST request
        
        UserEntity createdUser = userService.addUser(entity);
        return new ResponseEntity<UserEntity>(createdUser,HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<UserEntity> getMethodName(@PathVariable int id) {
        
        UserEntity user = userService.getById(id);

        if(user == null)
        {
            return new ResponseEntity<UserEntity>(user,HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<UserEntity>(user,HttpStatus.OK);
        }
        
    }
    
    
}

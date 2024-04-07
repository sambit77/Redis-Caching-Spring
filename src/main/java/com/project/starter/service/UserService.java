package com.project.starter.service;

import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Service;

import com.project.starter.data.UserEntity;
import com.project.starter.exchange.AddUserRequest;
import com.project.starter.reposiotyservice.UserRepositoryService;
import com.project.starter.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRepositoryService userRepositoryService;
    private static int id = 0;

    public UserEntity addUser(AddUserRequest entity)
    {
        UserEntity user = new UserEntity();
        user.setName(entity.getName());
        id++;
        user.setUserId(id);
        return userRepository.save(user);
    }

    public UserEntity getById(int id)
    {
        return userRepositoryService.getById(id);
    }
}

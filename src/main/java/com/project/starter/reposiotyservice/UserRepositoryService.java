package com.project.starter.reposiotyservice;

import org.springframework.stereotype.Service;

import com.project.starter.data.UserEntity;
import com.project.starter.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRepositoryService {
    private final UserRepository userRepository;

    public UserEntity getById(int id)
    {
        return userRepository.findByUserId(id);
    }
}

package com.project.starter.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.starter.data.UserEntity;
public interface UserRepository extends MongoRepository<UserEntity,String>
{
    UserEntity findByUserId(int id);   
}

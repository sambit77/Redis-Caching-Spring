package com.project.starter.reposiotyservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.starter.config.RedisConfiguration;
import com.project.starter.data.UserEntity;
import com.project.starter.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import redis.clients.jedis.Jedis;

@Service
@RequiredArgsConstructor
public class UserRepositoryService {

    private final UserRepository userRepository;

    @Autowired
    private RedisConfiguration redisConfiguration;

    public UserEntity getById(int id)
    {
        UserEntity user = null;
        if(redisConfiguration.isCacheAvailable())
        {
            //System.out.println("Cache Available");
            user = getFromCache(id);
        }
        else
        {
            //System.out.println("Cache Not Available");
            user = getFromDatabase(id);
        }

        return user;
        
    }

    private UserEntity getFromDatabase(int id) {
        // TODO Auto-generated method stub
        System.out.println("Fetched Entity From Database");
        return userRepository.findByUserId(id);
    }

    private UserEntity getFromCache(int id) {
        // TODO Auto-generated method stub
        UserEntity user = null;
        
        try(Jedis jedis = redisConfiguration.getJedisPool().getResource())
        {
            String jsonStringFromCache = jedis.get(Integer.toString(id));

            if(jsonStringFromCache == null)
            {
                //Fetch entry from db and update the cache
                String createdJsonString = "";
                try {
                    user = getFromDatabase(id);
                    createdJsonString = new ObjectMapper().writeValueAsString(user);
                  } catch (JsonProcessingException e) {
                    e.printStackTrace();
                  }
           
                  // Do operations with jedis resource
                  jedis.setex(Integer.toString(id),5,createdJsonString);

            }
            else
            {
                System.out.println("Fetched Entity From Cache Memory");
                try
                {
                    user = new ObjectMapper().readValue(jsonStringFromCache, new TypeReference<UserEntity>() {});
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        return user;
    }
}

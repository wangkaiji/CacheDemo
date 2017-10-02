package com.jek.service;

import com.jek.enity.User;
import com.jek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users",key="'user'+#id",unless = "#result==null")
    public User get(Integer id){
        return userRepository.findOne(id);
    }

    @CacheEvict(value = "users",key = "'user'+#user.id")
    @Transactional(readOnly = false)
    public void update(User user){
        userRepository.save(user);
    }

    @CacheEvict(value = "users",key = "'user'+#user.id")
    @Transactional(readOnly = false)
    public void delete(User user){
        userRepository.delete(user);
    }

}

package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepositoryByName extends Repository<User,Integer> {
    List<User> findByName(String name);
    
    List<User> findByNameAndAge(String name,Integer age);
    
    List<User> findByNameLike(String name);
}

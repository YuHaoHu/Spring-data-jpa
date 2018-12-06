package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JpaSpecificationExecutor 注解
 */
public interface UserRepositoryJpa extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {
    
//    User findOne(int i);
}

package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository
 */
public interface UserRepository extends JpaRepository <User,Integer> {

}

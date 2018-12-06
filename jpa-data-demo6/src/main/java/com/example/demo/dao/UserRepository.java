package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * JpaRepository
 */
public interface UserRepository extends JpaRepository <User,Integer> {
    @Transactional
    @Query("update User set name=:name where id=:id")
    @Modifying
    void updateUserNameById(@Param("name") String name,@Param("id") Integer id);
    
   User getUserById(Integer id);
}

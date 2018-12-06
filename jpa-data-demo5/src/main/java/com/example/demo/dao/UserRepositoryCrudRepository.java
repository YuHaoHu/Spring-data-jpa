package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.repository.CrudRepository;

/**
 * CrudRepository接口
 */
public interface UserRepositoryCrudRepository extends CrudRepository<User,Integer> {

}

package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository接口
 */
public interface UserRepositoryQuery extends Repository<User,Integer> {
    
//    @Query(value = "select * from t_user where name=?",nativeQuery = true)
//List<User> queryByName(String name);
    
    //数据更新
    @Query("update User set name=:name where id=:id")
    @Modifying
    void updateUserNameById(String name,Integer id);
}

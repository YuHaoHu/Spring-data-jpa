package com.example.demo;
/**
 * 一对多的关联关系测试
 */

import com.example.demo.dao.UserRepository;
import com.example.demo.dao.UserRepositoryJpa;
import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneToManyTest {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * 一对多的关联关系的
     * 添加
     */
    @Test
    public void testSave(){
        //1.创建一个角色
        Role role = new Role();
        role.setRolename("班长");
        
        // 2.创建一个用户
        
        User user = new User();
        user.setName("小八");
        user.setAge(18);
        user.setAddress("天津");
        //3.关联
        role.getUser().add(user);
        user.setRole(role);
        
        //4.保存
        this.userRepository.save(user);
    }
    
    /**
     * 一对多的关联关系的
     * 查询
     */
    @Test
    public void testfindOne(){
      User finOne = userRepository.getUserById(5);
        System.out.println(finOne);
        Role role= finOne.getRole();
        System.out.println(role.getRolename());
    }
}

package com.example.demo;

import com.example.demo.dao.*;
import com.example.demo.pojo.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryByName userRepositoryByName;
    @Autowired
    private UserRepositoryQuery userRepositoryQuery;
    @Autowired
    private UserRepositoryCrudRepository userRepositoryCrudRepository;
    @Autowired
    private UserRepositoryPagingAndShortRepository userRepositoryPagingAndShortRepository;
    
    
    
    
    //添加
    @Test
    @Ignore
    public void addUser() {
    
        User user = new User();
        user.setName("李四四");
        user.setAge(19);
        user.setAddress("山东菏泽");
        this.userRepository.save(user);
    }
    
    /**
     * Repository测试--根据姓名查询
     */
    @Test
    @Ignore
    public void addUserByname() {
        List<User> list=userRepositoryByName.findByName("胡玉浩");
        for (User user:list) {
            System.out.println(list);
        }
    }
    
    /**
     * Repository测试--根据姓名和年龄查询
     */
    @Test

    public void addUserBynameAndage() {
        List<User> list=userRepositoryByName.findByNameAndAge("胡玉浩",18);
        for (User user:list) {
            System.out.println(list);
        }
    }
    
    /**
     * Repository--------@Qurey
     */
    @Test
    @Ignore
//    public void testUserRepositoryQuery() {
//        List<User> list=userRepositoryQuery.queryByName("胡玉浩");
//        for (User user:list) {
//            System.out.println(list);
//        }
//    }
    
    /**
     * Repository--------@Qurey--更新操作
     */
//    @Test
//    @Transactional     // @Transactional和  @Test 一起使用是回滚
//    @Rollback(false)  //取消回滚
    @Test
    public void testUpdateUserNameById() {
       
        this.userRepositoryQuery.updateUserNameById("李思思",4);
    }
    
    /**
     * CrudRepository测试--save插入
     */
    @Test
    @Ignore
    @Transactional
    public void testUserCrudRepositorySave(){
        User user =new User();
        user.setName("王五");
        user.setAge(19);
        user.setAddress("北京朝阳");
        this.userRepositoryCrudRepository.save(user);
    }
    /**
     * CrudRepository测试--save删除
     */
    @Test
    @Ignore
    public void testUserCrudRepositoryDelete(){
        User user =new User();
        user.setId(3);
        this.userRepositoryCrudRepository.delete(user);
    }
    
    /**
     *
     */
    @Test
    public void testUserPagingAndShortRepository(){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
      List<User> list= (List<User>) this.userRepositoryPagingAndShortRepository.findAll(sort);
        for (User user:list) {
            System.out.println(list);
        }
    }
    
}

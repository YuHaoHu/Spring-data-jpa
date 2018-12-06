package com.example.demo;

import com.example.demo.dao.UserRepository;
import com.example.demo.dao.UserRepositoryByName;
import com.example.demo.dao.UserRepositoryJpa;
import com.example.demo.dao.UserRepositoryPagingAndShortRepository;
import com.example.demo.pojo.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryByName userRepositoryByName;
    @Autowired
    private UserRepositoryPagingAndShortRepository userRepositoryPagingAndShortRepository;
    @Autowired
    private UserRepositoryJpa userRepositoryJpa;
    
    //JpaRepository:添加
    @Test
    public void addUser() {
        User user = new User();
        user.setName("小六六");
        user.setAge(19);
        user.setAddress("山东");
        this.userRepository.save(user);
    }
    
    /**
     * JpaRepository:删除
     */
    
    @Test
    public void delUser() {
        this.userRepository.deleteById(6);
    }
    
    /**
     * JpaRepository:修改
     */
    @Test
    public void updateUser() {
        this.userRepository.updateUserNameById("小七", 5);
    }
    
    /**
     * JpaRepository:查询
     */
//    @Test
//    public void selectUser(){
//        User user = userRepositoryJpa.findOne(1);
//
//    }
//
    
    /**
     * Repository
     * 测试--根据姓名查询
     */
    @Test
//    @Ignore
    public void addUserByname() {
        List<User> list = userRepositoryByName.findByName("胡玉浩");
        for (User user : list) {
            System.out.println(list);
        }
    }
    
    /**
     * Repository测试--根据姓名和年龄查询
     */
    @Test
    
    public void addUserBynameAndage() {
        List<User> list = userRepositoryByName.findByNameAndAge("胡玉浩", 18);
        for (User user : list) {
            System.out.println(list);
        }
    }
    
    @Test
    public void testUserRepositoryLike() {
        List<User> list = userRepositoryByName.findByNameLike("李%");
//        for (User user:list) {
//            System.out.println(user);
//        }
        System.out.println(list);
    }
    
    
    /**
     * PagingAndShortRepository--排序
     */
    @Test
    public void testUserPagingAndShortRepositorySort() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<User> list = (List<User>) this.userRepositoryPagingAndShortRepository.findAll(sort);
//        for (User user:list) {
//            System.out.println(user);
//        }
        System.out.println(list);
    }
    
    /**
     * PagingAndShortRepository--分页
     */
    @Test
    public void testUserPagingAndShortRepositoryPage() {
        
        Pageable pageable = new PageRequest(0, 2);
        Page<User> page = this.userRepositoryPagingAndShortRepository.findAll(pageable);
        System.out.println(page.getTotalPages());
        System.out.println(page.getTotalElements());
        List<User> list = page.getContent();
        System.out.println(list);
    }
    
    
    /**
     * PagingAndShortRepository--排序与分页
     */
    @Test
    public void testUserPagingAndShortRepository() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0, 2, sort);
        Page<User> page = this.userRepositoryPagingAndShortRepository.findAll(pageable);
        
        System.out.println(page.getTotalPages());  ///每一页显示的信息条数
        System.out.println(page.getTotalElements());  //一共多少数据
        List<User> list = page.getContent();
        System.out.println(list);
    }
    
    /**
     * JpaRepository--排序
     */
    @Test
    public void testUserJpaRepositorySort() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<User> list = this.userRepository.findAll(sort);
        for (User user : list) {
            System.out.println(user);
        }
//        System.out.println(list);
    }
    
    /***
     *
     */
    
    /**
     * JpaSpecificationExecutor和JpaRepository
     * 测试--单个条件查询
     */
    @Test
    public void testUserRepositoryJpa() {
        
        /**
         *  Predicate :封装了单个的查询条件
         *  root:查询对象的属性封装
         *  CriteriaQuery<?> criteriaQuery：我们要执行的查询中的各个部分的信息：select ，from
         *  CriteriaBuilder criteriaBuilder:查询条件的构造器。定义不同的查询条件
         */
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                Predicate pre = criteriaBuilder.equal(root.get("name"), "胡玉浩");
                return pre;
            }
        };
        List<User> list = userRepositoryJpa.findAll(spec);
        for (User user : list
                ) {
            System.out.println(user);
        }
    }
    
    /**
     * JpaSpecificationExecutor和JpaRepository
     * 测试--多个条件查询:第一种方法
     */
    @Test
    public void testUserRepositoryJpa2() {
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder
                    cb) {
                //WHERE name = ? AND id = ?
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("name"), "张三"));
                list.add(cb.equal(root.get("age"), 15));
                Predicate[] arr = new Predicate[list.size()];
                return cb.and(list.toArray(arr));
            }
        };
        List<User> list = userRepositoryJpa.findAll(spec);
        for (User user : list
                ) {
            System.out.println(user);
        }
        
    }
    
    /**
     * JpaSpecificationExecutor和JpaRepository 测试--多个条件查询:第二种方法
     */
    @Test
    public void testUserRepositoryJpa3() {
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder cb) {
                //WHERE name = ? AND id = ?
//                List<Predicate> list = new ArrayList<>();
//                list.add(criteriaBuilder.equal(root.get("name"),"张三"));
//                list.add(criteriaBuilder.equal(root.get("age"),15));
//                Predicate []arr = new Predicate[list.size()];
                return cb.and(cb.equal(root.get("name"), "张三"), cb.equal(root
                        .get("age"), 15));
            }
        };
        List<User> list = userRepositoryJpa.findAll(spec);
        for (User user : list
                ) {
            System.out.println(user);
        }
    }
    
    /**
     * JpaSpecificationExecutor和JpaRepository 测试--多个条件查询:并进行分页和倒叙操作
     */
    @Test
    public void testUserRepositoryJpa4() {
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder cb) {
                //WHERE（ name = ? AND id = ?）or(id=?)
                return cb.or(cb.and(cb.equal(root.get("name"), "张三"),
                        cb.equal(root.get("age"), 15))
                        , cb.equal(root.get("id"), 1));
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0, 2, sort);
        Page<User> page = this.userRepositoryJpa.findAll(spec, pageable);
        System.out.println("每一页显示的信息条数:" + page.getTotalPages());  ///每一页显示的信息条数
        System.out.println("一共多少数据:" + page.getTotalElements());  //一共多少数据
        List<User> list = page.getContent();
        for (User user : list
                ) {
            System.out.println(user);
        }
        
    }
}

package com.example.demo;
/**
 * 一对多的关联关系测试
 */



import com.example.demo.dao.RoleRepository;
import com.example.demo.pojo.Menu;
import com.example.demo.pojo.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToManyTest {
    
    @Autowired
   private RoleRepository roleRepository;
    
    /**
     * 一对多的关联关系的
     * 添加
     */
    @Test
    public void testSave() {
    
        Role role = new Role();
        role.setRolename("项目经理");
    
        Menu menu = new Menu();
        menu.setMenuname("====项目管理系统====");
        menu.setFatherid(0);
        Menu menu1 = new Menu();
        menu1.setMenuname("====资产管理系统====");
        menu1.setFatherid(1);
        
        role.getMenu().add(menu);
        role.getMenu().add(menu1);
        menu.getRole().add(role);
        menu1.getRole().add(role);
        
        this.roleRepository.save(role);
        
    }
    
    /**
     * 查询
     */
//    private void testfind(){
//      Role role= this.roleRepository.findOne(3);
//        System.out.println(role);
//        Set<Menu> menu =role.getMenu();
//        for (Menu menu1:menu
//             ) {
//            System.out.println(menu1);
//        }
//    }
}

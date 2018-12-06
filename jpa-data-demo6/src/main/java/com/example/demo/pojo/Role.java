package com.example.demo.pojo;
/**
 * 测试 一对多的映射关系：
 * 一个用户只能有一个对象
 * 一个对象可以有多个用户
 */

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="t_role")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private Integer roleid;
    @Column(name = "rolename")
    private String rolename;
    
    @OneToMany(mappedBy = "role")
    private Set<User> user =new HashSet<>();
    
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "t_role_menu",joinColumns = @JoinColumn(name = "role_id"),inverseJoinColumns = @JoinColumn(name
            = "menu_id"))
    @JoinColumn()
    private Set<Menu> menu = new HashSet<>();
    public Integer getRoleid() {
        return roleid;
    }
    
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
    
    public String getRolename() {
        return rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public Set<User> getUser() {
        return user;
    }
    
    public void setUser(Set<User> user) {
        this.user = user;
    }
    
    public Set<Menu> getMenu() {
        return menu;
    }
    
    public void setMenu(Set<Menu> menu) {
        this.menu = menu;
    }
}

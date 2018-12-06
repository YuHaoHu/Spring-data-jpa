package com.example.demo.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_menu")
public class Menu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuid")
    private Integer menuid;
    @Column(name = "menuname")
    private String menuname;
    @Column(name = "menuurl")
    private String menuurl;
    @Column(name = "fatherid")
    private Integer fatherid;
    
    @ManyToMany(mappedBy ="menu")
    private Set<Role> role = new HashSet<>();
    
    public Integer getMenuid() {
        return menuid;
    }
    
    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }
    
    public String getMenuname() {
        return menuname;
    }
    
    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }
    
    public String getMenuurl() {
        return menuurl;
    }
    
    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl;
    }
    
    public Integer getFatherid() {
        return fatherid;
    }
    
    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }
    
    public Set<Role> getRole() {
        return role;
    }
    
    public void setRole(Set<Role> role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "Menu{" +
                "menuid=" + menuid +
                ", menuname='" + menuname + '\'' +
                ", menuurl='" + menuurl + '\'' +
                ", fatherid=" + fatherid +
                '}';
    }
}

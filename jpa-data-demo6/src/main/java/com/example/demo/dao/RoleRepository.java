package com.example.demo.dao;

import com.example.demo.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
//    Role getRoleById(Integer id);
}

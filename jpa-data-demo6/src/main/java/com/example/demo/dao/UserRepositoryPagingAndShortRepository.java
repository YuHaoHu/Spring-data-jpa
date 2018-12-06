package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PagingAndSortingRepository接口
 */
public interface UserRepositoryPagingAndShortRepository extends PagingAndSortingRepository<User,Integer> {
}

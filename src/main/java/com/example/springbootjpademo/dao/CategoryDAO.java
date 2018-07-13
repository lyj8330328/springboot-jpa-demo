package com.example.springbootjpademo.dao;

import com.example.springbootjpademo.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {

}

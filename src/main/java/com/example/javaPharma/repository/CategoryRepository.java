package com.example.javaPharma.repository;


import com.example.javaPharma.pojo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

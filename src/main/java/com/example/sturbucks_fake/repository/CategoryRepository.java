package com.example.sturbucks_fake.repository;

import com.example.sturbucks_fake.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    boolean existsByName(String name);

}

package com.example.sturbucks_fake.repository;

import com.example.sturbucks_fake.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink,Integer> {

    boolean existsByName(String name);

}

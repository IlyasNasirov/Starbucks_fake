package com.example.sturbucks_fake.repository;

import com.example.sturbucks_fake.model.Bucket;
import com.example.sturbucks_fake.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);


}

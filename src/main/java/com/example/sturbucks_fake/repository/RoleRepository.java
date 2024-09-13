package com.example.sturbucks_fake.repository;

import com.example.sturbucks_fake.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(String name);

}

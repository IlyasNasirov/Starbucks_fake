package com.example.sturbucks_fake.repository;

import com.example.sturbucks_fake.model.Bucket;
import com.example.sturbucks_fake.model.BucketItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BucketRepository extends JpaRepository<Bucket, Integer> {

    Optional<Bucket> findByUserId(int userId);

}

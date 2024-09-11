package com.example.sturbucks_fake.repository;

import com.example.sturbucks_fake.model.BucketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketItemRepository extends JpaRepository<BucketItem, Integer> {


}

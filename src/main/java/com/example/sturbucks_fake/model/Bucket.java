package com.example.sturbucks_fake.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "buckets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double totalPrice;

    /**
     * Связь один пользователь одна корзина
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Связь одна корзина много элементов
     */
    @OneToMany(mappedBy = "bucket", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<BucketItem> items;

}

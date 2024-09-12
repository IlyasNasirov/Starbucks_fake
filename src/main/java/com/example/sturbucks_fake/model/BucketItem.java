package com.example.sturbucks_fake.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "bucket_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BucketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    /**
     * связь много элементов одна корзина
     */
    @ManyToOne
    @JoinColumn(name = "bucket_id")
    @JsonBackReference
    private Bucket bucket;

    /**
     * связь много элементов один напиток
     */
    @ManyToOne
    @JoinColumn(name = "drink_id")

    private Drink drink;

}

package com.example.sturbucks_fake.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "drinks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    private String description;

    private boolean available;

    private long price;

    /**
     * связь много напитков одна категория
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * связь один напиток у многих элементов корзины
     */
    @OneToMany(mappedBy = "drink",cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE})
    private List<BucketItem> bucketItems;

}

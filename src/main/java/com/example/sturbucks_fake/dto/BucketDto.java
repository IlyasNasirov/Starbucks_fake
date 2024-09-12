package com.example.sturbucks_fake.dto;

import com.example.sturbucks_fake.model.BucketItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BucketDto {

    private int userId;

    private List<BucketItemDto> items;

}

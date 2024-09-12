package com.example.sturbucks_fake.mapper;

import com.example.sturbucks_fake.dto.BucketItemDto;
import com.example.sturbucks_fake.model.BucketItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DrinkMapper.class)
public interface BucketItemMapper {

    BucketItemDto toDto(BucketItem bucketItem);

    BucketItem toEntity(BucketItemDto bucketItemDto);

}

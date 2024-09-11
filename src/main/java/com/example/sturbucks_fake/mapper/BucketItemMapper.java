package com.example.sturbucks_fake.mapper;

import com.example.sturbucks_fake.dto.BucketItemDto;
import com.example.sturbucks_fake.model.BucketItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BucketItemMapper {

    @Mapping(target = "bucketId", source = "bucket.id")
//    @Mapping(target = "userId", source = "user.id")
    BucketItemDto toDto(BucketItem bucketItem);

    @Mapping(target = "bucket.id", source = "bucketId")
//    @Mapping(target = "user.id", source = "userId")
    BucketItem toEntity(BucketItemDto bucketItemDto);

}

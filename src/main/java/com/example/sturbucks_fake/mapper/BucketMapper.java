package com.example.sturbucks_fake.mapper;

import com.example.sturbucks_fake.dto.BucketDto;
import com.example.sturbucks_fake.model.Bucket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BucketMapper {

    @Mapping(target = "userId", source = "user.id")
    BucketDto toDto(Bucket bucket);

    @Mapping(target = "user.id", source = "userId")
    Bucket toEntity(BucketDto bucketDto);

}

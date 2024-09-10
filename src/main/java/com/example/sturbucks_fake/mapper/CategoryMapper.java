package com.example.sturbucks_fake.mapper;

import com.example.sturbucks_fake.dto.CategoryDto;
import com.example.sturbucks_fake.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

}

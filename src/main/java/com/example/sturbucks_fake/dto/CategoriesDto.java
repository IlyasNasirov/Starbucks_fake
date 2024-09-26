package com.example.sturbucks_fake.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CategoriesDto {

    private List<CategoryDto> categories;

}

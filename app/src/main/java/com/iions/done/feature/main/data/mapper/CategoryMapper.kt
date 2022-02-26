package com.iions.done.feature.main.data.mapper

import com.iions.done.feature.main.data.model.CategoryResponse
import com.iions.entity.CategoryEntity

object CategoryMapper {
    fun mapToLocal(category: List<CategoryResponse>): List<CategoryEntity> {
        return category.map {
            CategoryEntity(
                categoryName = it.name,
                categoryIcon = it.image
            )
        }
    }
}
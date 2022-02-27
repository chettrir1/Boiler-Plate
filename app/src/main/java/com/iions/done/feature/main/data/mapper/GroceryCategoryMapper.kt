package com.iions.done.feature.main.data.mapper

import com.iions.done.feature.main.data.model.GroceryCategoryResponse
import com.iions.entity.GroceryCategoryEntity

object GroceryCategoryMapper {
    fun mapToLocal(category: GroceryCategoryResponse): GroceryCategoryEntity {
        return GroceryCategoryEntity(
            id = category.id ?: 0,
            categoryName = category.name ?: ""
        )
    }
}
package com.iions.done.feature.main.data.mapper

import com.iions.done.feature.main.data.model.HomeGroceryCategoryResponse
import com.iions.entity.GroceryCategoryEntity

object GroceryCategoryMapper {
    fun mapToLocal(category: HomeGroceryCategoryResponse): GroceryCategoryEntity {
        return GroceryCategoryEntity(
            id = category.id ?: 0,
            categoryName = category.name ?: ""
        )
    }
}
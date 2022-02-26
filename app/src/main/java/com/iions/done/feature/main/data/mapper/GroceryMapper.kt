package com.iions.done.feature.main.data.mapper

import com.iions.done.feature.main.data.model.GroceryResponse
import com.iions.entity.GroceryEntity

object GroceryMapper {
    fun mapToLocal(grocery: List<GroceryResponse>): List<GroceryEntity> {
        return grocery.map {
            GroceryEntity(
                groceryId = it.id,
                name = it.name,
                sku = it.sku ?: "",
                image = it.image ?: "",
                categoryId = it.categoryId ?: -1,
                brandId = it.brandId ?: -1,
                hasVarient = it.hasVarient ?: 0,
                parentId = it.parentId ?: -1
            )
        }
    }
}
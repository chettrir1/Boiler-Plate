package com.iions.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iions.done.feature.main.data.model.GroceryRemoteResponse
import com.iions.done.feature.main.data.model.GroceryResponse
import com.iions.entity.GroceryEntity

@Dao
interface GroceryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: List<GroceryEntity>)

    @Query(
        """select grocery_id as id,
        grocery_sku as sku,
        grocery_name as name,
        grocery_cover_image as image,
        grocery_category_id as categoryId,
        grocery_brand_id as brandId,
        grocery_has_variant as hasVarient,
        grocery_parent_id as parentId
        from grocery"""
    )
    suspend fun getGroceryResponse(): List<GroceryResponse>?
}
package com.iions.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iions.done.feature.main.data.model.HomeGroceryResponse
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
        grocery_parent_id as parentId,
        grocery_price as price,
        grocery_old_price as oldPrice,
        grocery_has_discount as hasDiscount,
        grocery_average_rating as rating
        from grocery
        where grocery_category_id =:categoryId"""
    )
    fun getGroceryResponse(categoryId: Int): LiveData<List<HomeGroceryResponse>>
}
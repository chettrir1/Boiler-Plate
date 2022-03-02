package com.iions.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iions.done.feature.main.data.model.GroceryCategoryResponse
import com.iions.entity.GroceryCategoryEntity

@Dao
interface GroceryCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: GroceryCategoryEntity)

    @Query(
        """select grocery_category_id as id,
        grocery_category_name as name
        from grocery_category"""
    )
    fun getGroceryCategoryResponse(): List<GroceryCategoryResponse>?
}
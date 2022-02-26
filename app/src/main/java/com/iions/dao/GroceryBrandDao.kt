package com.iions.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.iions.entity.GroceryBrandEntity

@Dao
interface GroceryBrandDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: GroceryBrandEntity)
}
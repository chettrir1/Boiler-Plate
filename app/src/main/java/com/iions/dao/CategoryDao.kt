package com.iions.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iions.done.feature.main.data.model.CategoryResponse
import com.iions.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: List<CategoryEntity>)

    @Query(
        """select category_name as name,
        category_icon as image
        from category """
    )
    suspend fun getCategoryResponse(): List<CategoryResponse>
}
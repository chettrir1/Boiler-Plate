package com.iions.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iions.done.feature.main.data.model.ModuleResponse
import com.iions.entity.ModulesEntity

@Dao
interface ModuleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: List<ModulesEntity>)

    @Query(
        """select module_name as name,
        module_icon as image
        from module"""
    )
    fun getCategoryResponse(): LiveData<List<ModuleResponse>>
}
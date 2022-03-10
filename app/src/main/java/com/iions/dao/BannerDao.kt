package com.iions.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iions.done.feature.main.data.model.BannerResponse
import com.iions.entity.BannerEntity

@Dao
interface BannerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: List<BannerEntity>)

    @Query(
        """select banner_id as id,
        banner_image as url
        from banner"""
    )
    suspend fun getBannerResponse(): List<BannerResponse>
}
package com.iions.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iions.done.feature.main.data.model.HomeRestaurantRemoteResponse
import com.iions.entity.RestaurantEntity

@Dao
interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: List<RestaurantEntity>)

    @Query(
        """select restaurant_id as id,
        restaurant_name as name,
        restaurant_logo as logo,
        restaurant_cover_image as coverImage,
        restaurant_address as address,
        restaurant_description as description,
        restaurant_latitude as latitude,
        restaurant_longitude as longitude
        from restaurant"""
    )
    suspend fun getRestaurantResponse(): List<HomeRestaurantRemoteResponse>?
}
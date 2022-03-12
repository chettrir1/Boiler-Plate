package com.iions.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iions.done.feature.auth.data.model.UserAddressResponse
import com.iions.entity.UserAddressEntity

@Dao
interface UserAddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: List<UserAddressEntity>)

    @Query(
        """select district_id as id,
         street_id as id,
         local_address as localAddress
        from address"""
    )
    suspend fun getUserAddressResponse(): List<UserAddressResponse>
}
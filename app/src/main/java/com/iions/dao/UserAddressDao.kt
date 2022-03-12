package com.iions.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.entity.UserAddressEntity

@Dao
interface UserAddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: List<UserAddressEntity>)

    @Query(
        """select user_address_id as addressId,
            district_id as districtId,
            district_name as district,
            street_id as streetId,
            street_name as street,
            local_address as localAddress
        from address"""
    )
    suspend fun getUserAddressResponse(): List<AddressResponse>
}
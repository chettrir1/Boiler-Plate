package com.iions.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iions.done.feature.main.data.model.DistrictResponse
import com.iions.entity.DistrictEntity

@Dao
interface DistrictDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: List<DistrictEntity>)

    @Query(
        """select district_id as id,
        district_name as name
        from district"""
    )
    suspend fun getDistrictResponse(): List<DistrictResponse>

    @Query(
        """ select district_id as id,
        district_name as name from district where district_id=:districtId"""
    )
    suspend fun getDistrictResponseWithId(districtId: Int): DistrictResponse
}
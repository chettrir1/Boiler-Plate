package com.iions.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iions.done.feature.main.data.model.StreetResponse
import com.iions.entity.StreetEntity

@Dao
interface StreetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: List<StreetEntity>)

    @Query(
        """select street_id as id,
        street_name as name
        from street
        where district_id =:districtId"""
    )
    suspend fun getStreetResponse(districtId: Int): List<StreetResponse>

    @Query(
        """select street_id as id,
        street_id as name
        from street where street_id=:streetId"""
    )
    suspend fun getStreetResponseWithId(streetId: Int): StreetResponse
}
package com.iions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iions.Constants

@Entity(tableName = Constants.TBL_DISTRICT)
data class DistrictEntity(
    @PrimaryKey
    @ColumnInfo(name = Constants.DISTRICT_ID) var id: Int,
    @ColumnInfo(name = Constants.DISTRICT_NAME) var name: String
)
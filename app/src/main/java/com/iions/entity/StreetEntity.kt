package com.iions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iions.Constants

@Entity(tableName = Constants.TBL_STREET)
data class StreetEntity(
    @PrimaryKey
    @ColumnInfo(name = Constants.STREET_ID) var id: Int,
    @ColumnInfo(name = Constants.STREET_NAME) var name: String,
    @ColumnInfo(name = Constants.DISTRICT_ID) var districtId: Int
)
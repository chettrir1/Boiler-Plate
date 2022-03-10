package com.iions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iions.Constants

@Entity(tableName = Constants.TBL_BANNER)
data class BannerEntity(
    @PrimaryKey
    @ColumnInfo(name = Constants.BANNER_ID) var id: Int,
    @ColumnInfo(name = Constants.BANNER_IMAGE) var image: String,
)
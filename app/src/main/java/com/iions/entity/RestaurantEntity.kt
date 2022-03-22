package com.iions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iions.Constants

@Entity(tableName = Constants.TBL_RESTAURANT)
data class RestaurantEntity(
    @PrimaryKey
    @ColumnInfo(name = Constants.RESTAURANT_ID) var restaurantId: Int,
    @ColumnInfo(name = Constants.RESTAURANT_NAME) var restaurantName: String,
    @ColumnInfo(name = Constants.RESTAURANT_LOGO) var restaurantLogo: String,
    @ColumnInfo(name = Constants.RESTAURANT_IMAGE) var restaurantImage: String,
    @ColumnInfo(name = Constants.RESTAURANT_ADDRESS) var restaurantAddress: String,
    @ColumnInfo(name = Constants.RESTAURANT_DESCRIPTION) var restaurantDescription: String,
    @ColumnInfo(name = Constants.RESTAURANT_LAT) var restaurantLatitude: String,
    @ColumnInfo(name = Constants.RESTAURANT_LNG) var restaurantLongitude: String
)
package com.iions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iions.Constants

@Entity(tableName = Constants.TBL_GROCERY_BRAND)
data class GroceryBrandEntity(
    @PrimaryKey
    @ColumnInfo(name = Constants.GROCERY_BRAND_ID) var id: Int,
    @ColumnInfo(name = Constants.GROCERY_BRAND_NAME) var brandName: String,
    @ColumnInfo(name = Constants.GROCERY_BRAND_LOGO) var brandLogo: String
)
package com.iions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iions.Constants

@Entity(tableName = Constants.TBL_GROCERY_CATEGORY)
data class GroceryCategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = Constants.GROCERY_CATEGORY_ID) var id: Int,
    @ColumnInfo(name = Constants.GROCERY_CATEGORY_NAME) var categoryName: String
)
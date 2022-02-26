package com.iions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iions.Constants

@Entity(tableName = Constants.TBL_CATEGORY)
data class CategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = Constants.CATEGORY_NAME) var categoryName: String,
    @ColumnInfo(name = Constants.CATEGORY_ICON) var categoryIcon: String
)
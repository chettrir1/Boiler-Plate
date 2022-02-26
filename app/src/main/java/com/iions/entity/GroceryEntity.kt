package com.iions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iions.Constants

@Entity(tableName = Constants.TBL_GROCERY)
data class GroceryEntity(
    @PrimaryKey
    @ColumnInfo(name = Constants.GROCERY_ID) var groceryId: Int,
    @ColumnInfo(name = Constants.GROCERY_SKU) var sku: String,
    @ColumnInfo(name = Constants.GROCERY_NAME) var name: String,
    @ColumnInfo(name = Constants.GROCERY_COVER_IMAGE) var image: String,
    @ColumnInfo(name = Constants.GROCERY_CATEGORY_ID) var categoryId: Int,
    @ColumnInfo(name = Constants.BRAND_ID) var brandId: Int,
    @ColumnInfo(name = Constants.GROCERY_HAS_VARIANT) var hasVarient: Int,
    @ColumnInfo(name = Constants.GROCERY_PARENT_ID) var parentId: Int
)
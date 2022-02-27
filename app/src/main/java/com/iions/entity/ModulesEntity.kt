package com.iions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iions.Constants

@Entity(tableName = Constants.TBL_MODULE)
data class ModulesEntity(
    @PrimaryKey
    @ColumnInfo(name = Constants.MODULES_NAME) var modulesName: String,
    @ColumnInfo(name = Constants.MODULES_ICON) var modulesIcon: String
)
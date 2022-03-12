package com.iions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iions.Constants

@Entity(tableName = Constants.TBL_USER_ADDRESS)
data class UserAddressEntity(
    @PrimaryKey
    @ColumnInfo(name = Constants.USER_ADDRESS_ID) var id: Int,
    @ColumnInfo(name = Constants.USER_ADDRESS_DISTRICT_ID) var districtId: Int,
    @ColumnInfo(name = Constants.USER_ADDRESS_STREET_ID) var streetId: Int,
    @ColumnInfo(name = Constants.USER_ADDRESS_LOCAL) var localAddress: String
)
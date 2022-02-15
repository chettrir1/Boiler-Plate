package com.iions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iions.Constants

@Entity(tableName = Constants.TBL_USERS)
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = Constants.USER_ID) var userId: Long,
    @ColumnInfo(name = Constants.USER_FULL_NAME) var fullName: String,
    @ColumnInfo(name = Constants.USER_EMAIL) var email: String?,
    @ColumnInfo(name = Constants.USER_PHONE_NUMBER) var phoneNumber: String?,
    @ColumnInfo(name = Constants.USER_FIRST_NAME) var firstName: String,
    @ColumnInfo(name = Constants.USER_LAST_NAME) var lastName: String,
    @ColumnInfo(name = Constants.USER_ROLE_ID) var roleId: Long,
    @ColumnInfo(name = Constants.USER_ROLE) var role: String,
    @ColumnInfo(name = Constants.USER_PROFILE_IMAGE) var imageUrl: String
)
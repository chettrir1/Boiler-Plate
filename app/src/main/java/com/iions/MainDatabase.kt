package com.iions

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iions.dao.CategoryDao
import com.iions.dao.GroceryBrandDao
import com.iions.dao.GroceryDao
import com.iions.dao.UserDao
import com.iions.entity.CategoryEntity
import com.iions.entity.GroceryBrandEntity
import com.iions.entity.GroceryEntity
import com.iions.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        CategoryEntity::class,
        GroceryEntity::class,
        GroceryBrandEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {

    companion object {

        @Volatile
        var instance: MainDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MainDatabase {
            if (instance == null) {
                instance = createInstance(context)
            }
            return instance as MainDatabase
        }

        private fun createInstance(context: Context): MainDatabase {
            return Room
                .databaseBuilder(context, MainDatabase::class.java, Constants.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun getUsersDao(): UserDao
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getGroceryDao(): GroceryDao
    abstract fun getGroceryBrandDao(): GroceryBrandDao
}
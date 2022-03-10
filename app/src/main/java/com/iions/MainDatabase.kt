package com.iions

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iions.dao.*
import com.iions.entity.*

@Database(
    entities = [
        UserEntity::class,
        ModulesEntity::class,
        GroceryEntity::class,
        GroceryBrandEntity::class,
        GroceryCategoryEntity::class,
        BannerEntity::class
    ],
    version = 2,
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
    abstract fun getModuleDao(): ModuleDao
    abstract fun getGroceryDao(): GroceryDao
    abstract fun getGroceryBrandDao(): GroceryBrandDao
    abstract fun getGroceryCategoryDao(): GroceryCategoryDao
    abstract fun getBannerDao(): BannerDao
}
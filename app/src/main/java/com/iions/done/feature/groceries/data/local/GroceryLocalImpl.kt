package com.iions.done.feature.groceries.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.groceries.data.GroceryRepository
import javax.inject.Inject

class GroceryLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : GroceryRepository.Local {
    override fun getUserId(): Int {
        return sharedPreferenceManager.userId
    }
}

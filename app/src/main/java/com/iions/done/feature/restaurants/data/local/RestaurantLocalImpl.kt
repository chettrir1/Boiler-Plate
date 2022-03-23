package com.iions.done.feature.restaurants.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.groceries.data.GroceryRepository
import com.iions.done.feature.restaurants.data.RestaurantRepository
import javax.inject.Inject

class RestaurantLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : RestaurantRepository.Local {

    override fun getAuthorizationToken(): String {
        return sharedPreferenceManager.accessToken.toString()
    }

    override fun isUserLoggedIn(): Boolean {
        return sharedPreferenceManager.username.isNotEmpty()
    }

    override fun getUserId(): Int {
        return sharedPreferenceManager.userId
    }
}

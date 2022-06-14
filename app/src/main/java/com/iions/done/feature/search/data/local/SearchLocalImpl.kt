package com.iions.done.feature.search.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.search.data.SearchRepository
import javax.inject.Inject

class SearchLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : SearchRepository.Local {
}

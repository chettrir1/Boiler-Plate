package com.iions.done.feature.summary.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.summary.data.SummaryRepository
import javax.inject.Inject

class SummaryLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : SummaryRepository.Local {
}

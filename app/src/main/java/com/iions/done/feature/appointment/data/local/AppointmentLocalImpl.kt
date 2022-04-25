package com.iions.done.feature.appointment.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.appointment.data.AppointmentRepository
import javax.inject.Inject

class AppointmentLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : AppointmentRepository.Local {
}

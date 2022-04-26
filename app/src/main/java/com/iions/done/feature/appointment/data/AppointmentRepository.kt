package com.iions.done.feature.appointment.data

import com.iions.done.feature.appointment.data.model.AppointmentRemoteBaseResponse

interface AppointmentRepository {
    suspend fun getAppointment(
        filter: String?,
        page: Int
    ): AppointmentRemoteBaseResponse?

    interface Local {
    }

    interface Remote {
        suspend fun getAppointment(
            filter: String?,
            page: Int
        ): AppointmentRemoteBaseResponse?
    }
}
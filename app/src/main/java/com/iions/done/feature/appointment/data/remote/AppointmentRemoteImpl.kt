package com.iions.done.feature.appointment.data.remote

import com.iions.done.exceptions.FailedResponseException
import com.iions.done.feature.appointment.data.AppointmentRepository
import com.iions.done.feature.appointment.data.model.AppointmentRemoteBaseResponse
import com.iions.done.remote.ApiService
import javax.inject.Inject

class AppointmentRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : AppointmentRepository.Remote {

    override suspend fun getAppointment(
        filter: String?,
        page: Int
    ): AppointmentRemoteBaseResponse? {
        val params: MutableMap<String, String> = HashMap()
        params["filter"] = filter ?: ""
        val remoteResponse = apiService.getAppointment(page)
        if (remoteResponse.status == true) {
            throw FailedResponseException(
                remoteResponse.status!!,
                remoteResponse.message.toString()
            )
        } else {
            return remoteResponse.response
        }
    }
}
package com.iions.done.feature.appointment.data

import com.iions.done.feature.appointment.screens.AppointmentRemoteBaseResponse
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: AppointmentRepository.Local,
    private val remoteRepository: AppointmentRepository.Remote
) : AppointmentRepository {

    override suspend fun getAppointment(
        filter: String?,
        page: Int
    ): AppointmentRemoteBaseResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.getAppointment(filter, page)
        }
    }
}

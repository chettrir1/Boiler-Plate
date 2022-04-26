package com.iions.done.feature.appointment.screens

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.appointment.data.AppointmentRepository
import com.iions.done.feature.appointment.data.model.AppointmentRemoteBaseResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentViewModel @Inject constructor(
    var app: Application,
    private val repository: AppointmentRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val appointmentUseCase = MutableLiveData<Response<AppointmentRemoteBaseResponse>>()
    val appointmentResponse: LiveData<Response<AppointmentRemoteBaseResponse>> =
        appointmentUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun getAppointment(filter: String?, page: Int) {
        viewModelScope.launch {
            appointmentUseCase.value = Response.loading()
            try {
                appointmentUseCase.value = Response.complete(
                    repository.getAppointment(filter, page)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                appointmentUseCase.value = Response.error(error)
            }
        }
    }

}
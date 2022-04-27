package com.iions.done.feature.main.screens.camera.upload

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.CreateOrderResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    var app: Application,
    private val repository: MainRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val orderUseCase = MutableLiveData<Response<CreateOrderResponse>>()
    val orderResponse: LiveData<Response<CreateOrderResponse>> =
        orderUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun requestLogout(file: File) {
        viewModelScope.launch {
            orderUseCase.value = Response.loading()
            try {
                orderUseCase.value = Response.complete(
                    repository.createOrder(file)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                orderUseCase.value = Response.error(error)
            }
        }
    }

    fun isUserLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }

}
package com.iions.done.feature.main.screens.history

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.OrdersBaseResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    var app: Application,
    private val repository: MainRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val historyUseCase = MutableLiveData<Response<OrdersBaseResponse>>()
    val historyResponse: LiveData<Response<OrdersBaseResponse>> =
        historyUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun isUserLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }

    fun fetchOrdersList() {
        viewModelScope.launch {
            historyUseCase.value = Response.loading()
            try {
                historyUseCase.value = Response.complete(
                    repository.fetchOrdersList()
                )
            } catch (error: Exception) {
                error.printStackTrace()
                historyUseCase.value = Response.error(error)
            }
        }
    }
}
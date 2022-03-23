package com.iions.done.feature.main.screens

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.auth.data.model.LogoutResponse
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var app: Application,
    private val repository: MainRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val logoutUseCase = MutableLiveData<Response<LogoutResponse>>()
    val logoutResponse: LiveData<Response<LogoutResponse>> =
        logoutUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun isUserLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }

    private fun getAuthorizationToken(): String {
        return repository.getAuthorizationToken()
    }

    fun requestLogout() {
        viewModelScope.launch {
            logoutUseCase.value = Response.loading()
            try {
                logoutUseCase.value = Response.complete(
                    repository.requestLogout(getAuthorizationToken())
                )
            } catch (error: Exception) {
                error.printStackTrace()
                logoutUseCase.value = Response.error(error)
            }
        }
    }

}
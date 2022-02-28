package com.iions.done.feature.auth.screens.login.smslogin

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.auth.data.AuthRepository
import com.iions.done.feature.auth.data.model.LoginResponse
import com.rosia.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SmsLoginViewModel @Inject constructor(
    var app: Application,
    private val repository: AuthRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val loginUseCase = MutableLiveData<Response<List<LoginResponse>>>()
    val loginResponse: LiveData<Response<List<LoginResponse>>> =
        loginUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun loginWithPhoneResponse(username: String) {
        viewModelScope.launch {
            loginUseCase.value = Response.loading()
            try {
                loginUseCase.value = Response.complete(
                    repository.loginWithPhone(username)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                loginUseCase.value = Response.error(error)
            }
        }
    }
}
package com.iions.done.feature.auth.screens.verifypin

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.R
import com.iions.done.feature.auth.data.AuthRepository
import com.iions.done.feature.auth.data.model.LoginResponse
import com.iions.done.feature.auth.data.model.VerifyPinResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyPinViewModel @Inject constructor(
    var app: Application,
    private val repository: AuthRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val verifyPinUseCase = MutableLiveData<Response<VerifyPinResponse>>()
    val verifyPinResponse: LiveData<Response<VerifyPinResponse>> =
        verifyPinUseCase

    private val loginUseCase = MutableLiveData<Response<LoginResponse>>()
    val loginResponse: LiveData<Response<LoginResponse>> =
        loginUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    private fun getLoggedInUserId(): String {
        return repository.getLoginUserId()
    }

    fun verifyPinResponse(pin: String) {
        viewModelScope.launch {
            verifyPinUseCase.value = Response.loading()
            try {
                verifyPinUseCase.value = Response.complete(
                    repository.verifyPinRequest(pin, getLoggedInUserId())
                )
            } catch (error: java.lang.IllegalStateException) {
                error.printStackTrace()
                verifyPinUseCase.value =
                    Response.error(Throwable(app.getString(R.string.enter_valid_otp)))
            } catch (error: Exception) {
                error.printStackTrace()
                verifyPinUseCase.value =
                    Response.error(Throwable(app.getString(R.string.enter_valid_otp)))
            }
        }
    }

    fun loginWithPhoneResponse() {
        viewModelScope.launch {
            loginUseCase.value = Response.loading()
            try {
                loginUseCase.value = Response.complete(
                    repository.loginWithPhone(getLoggedInUserId())
                )
            } catch (error: java.lang.IllegalStateException) {
                error.printStackTrace()
                verifyPinUseCase.value =
                    Response.error(Throwable(app.getString(R.string.enter_valid_mobile_number)))
            } catch (error: Exception) {
                error.printStackTrace()
                loginUseCase.value =
                    Response.error(Throwable(app.getString(R.string.enter_valid_mobile_number)))
            }
        }
    }
}
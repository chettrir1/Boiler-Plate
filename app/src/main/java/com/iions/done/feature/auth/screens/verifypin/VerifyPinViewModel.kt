package com.iions.done.feature.auth.screens.verifypin

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.auth.data.AuthRepository
import com.iions.done.feature.auth.data.model.LoginResponse
import com.rosia.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class VerifyPinViewModel @Inject constructor(
    var app: Application,
    private val repository: AuthRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val verifyPinUseCase = MutableLiveData<Response<LoginResponse>>()
    val verifyPinResponse: LiveData<Response<LoginResponse>> =
        verifyPinUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}
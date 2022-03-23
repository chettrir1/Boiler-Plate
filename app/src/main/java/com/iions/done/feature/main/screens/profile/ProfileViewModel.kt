package com.iions.done.feature.main.screens.profile

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.ProfileBaseResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    var app: Application,
    private val repository: MainRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val addressUseCase = MutableLiveData<Response<List<AddressResponse>>>()
    val addressResponse: LiveData<Response<List<AddressResponse>>> =
        addressUseCase

    private val profileUseCase = MutableLiveData<Response<ProfileBaseResponse>>()
    val profileResponse: LiveData<Response<ProfileBaseResponse>> =
        profileUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun fetchAddressList() {
        viewModelScope.launch {
            addressUseCase.value = Response.loading()
            try {
                addressUseCase.value = Response.complete(
                    repository.fetchAddressList()
                )
            } catch (error: Exception) {
                error.printStackTrace()
                addressUseCase.value = Response.error(error)
            }
        }
    }

    fun fetchProfileResponse() {
        viewModelScope.launch {
            profileUseCase.value = Response.loading()
            try {
                profileUseCase.value = Response.complete(
                    repository.fetchProfileResponse()
                )
            } catch (error: Exception) {
                error.printStackTrace()
                profileUseCase.value = Response.error(error)
            }
        }
    }

    fun isUserLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }
}
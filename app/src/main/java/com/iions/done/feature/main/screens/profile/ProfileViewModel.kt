package com.iions.done.feature.main.screens.profile

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.EditProfileResponse
import com.iions.done.feature.main.data.model.ProfileBaseResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    var app: Application,
    private val repository: MainRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val profileUseCase = MutableLiveData<Response<ProfileBaseResponse>>()
    val profileResponse: LiveData<Response<ProfileBaseResponse>> =
        profileUseCase

    private val editUseCase = MutableLiveData<Response<EditProfileResponse>>()
    val editResponse: LiveData<Response<EditProfileResponse>> =
        editUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
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

    fun editProfileResponse(avatar: File) {
        viewModelScope.launch {
            editUseCase.value = Response.loading()
            try {
                editUseCase.value = Response.complete(
                    repository.editProfile(avatar = avatar)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                editUseCase.value = Response.error(error)
            }
        }
    }
}
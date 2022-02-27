package com.iions.done.feature.splash.screens

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.main.data.model.HomeResponse
import com.iions.done.feature.splash.data.SplashRepository
import com.rosia.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    var app: Application,
    private val repository: SplashRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val homeUseCase = MutableLiveData<Response<HomeResponse>>()
    val homeResponse: LiveData<Response<HomeResponse>> =
        homeUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun getHomeResponse() {
        viewModelScope.launch {
            homeUseCase.value = Response.loading()
            try {
                homeUseCase.value = Response.complete(
                    repository.fetchHomeResponse()
                )
            } catch (error: Exception) {
                error.printStackTrace()
                homeUseCase.value = Response.error(error)
            }
        }
    }
}
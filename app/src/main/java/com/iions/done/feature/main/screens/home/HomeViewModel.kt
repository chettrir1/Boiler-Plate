package com.iions.done.feature.main.screens.home

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.*
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var app: Application,
    private val repository: MainRepository
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

    fun fetchModuleList(): LiveData<List<ModuleResponse>> {
        return repository.fetchModuleList()
    }

    fun fetchGroceryCategoryList(): LiveData<List<HomeGroceryCategoryResponse>> {
        return repository.fetchGroceryCategoryList()
    }

    fun fetchGroceryList(categoryId: Int): LiveData<List<HomeGroceryResponse>> {
        return repository.fetchGroceryList(categoryId)
    }

    fun fetchBannerList(): LiveData<List<BannerResponse>> {
        return repository.fetchBannerList()
    }

    fun fetchRestaurantList(): LiveData<List<HomeRestaurantRemoteResponse>> {
        return repository.fetchRestaurantList()
    }

}
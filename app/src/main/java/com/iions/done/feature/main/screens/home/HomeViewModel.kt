package com.iions.done.feature.main.screens.home

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.*
import com.rosia.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var app: Application,
    private val repository: MainRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val categoryUseCase = MutableLiveData<Response<List<ModuleResponse>>>()
    val categoryResponse: LiveData<Response<List<ModuleResponse>>> =
        categoryUseCase

    private val groceryUseCase = MutableLiveData<Response<List<GroceryResponse>>>()
    val groceryResponse: LiveData<Response<List<GroceryResponse>>> =
        groceryUseCase

    private val groceryCategoryUseCase = MutableLiveData<Response<List<GroceryCategoryResponse>>>()
    val groceryCategoryResponse: LiveData<Response<List<GroceryCategoryResponse>>> =
        groceryCategoryUseCase


    private val bannerUseCase = MutableLiveData<Response<List<BannerResponse>>>()
    val bannerResponse: LiveData<Response<List<BannerResponse>>> =
        bannerUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun fetchModuleList() {
        viewModelScope.launch {
            categoryUseCase.value = Response.loading()
            try {
                categoryUseCase.value = Response.complete(
                    repository.fetchModuleList()
                )
            } catch (error: Exception) {
                error.printStackTrace()
                categoryUseCase.value = Response.error(error)
            }
        }
    }

    fun fetchGroceryCategoryList() {
        viewModelScope.launch {
            groceryCategoryUseCase.value = Response.loading()
            try {
                groceryCategoryUseCase.value = Response.complete(
                    repository.fetchGroceryCategoryList()
                )
            } catch (error: Exception) {
                error.printStackTrace()
                groceryCategoryUseCase.value = Response.error(error)
            }
        }
    }

    fun fetchGroceryList() {
        viewModelScope.launch {
            groceryUseCase.value = Response.loading()
            try {
                groceryUseCase.value = Response.complete(
                    repository.fetchGroceryList()
                )
            } catch (error: Exception) {
                error.printStackTrace()
                groceryUseCase.value = Response.error(error)
            }
        }
    }

    fun fetchBannerList() {
        viewModelScope.launch {
            bannerUseCase.value = Response.loading()
            try {
                bannerUseCase.value = Response.complete(
                    repository.fetchBannerList()
                )
            } catch (error: Exception) {
                error.printStackTrace()
                bannerUseCase.value = Response.error(error)
            }
        }
    }


}
package com.iions.done.feature.main.screens.home

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.BannerResponse
import com.iions.done.feature.main.data.model.CategoryResponse
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

    private val categoryUseCase = MutableLiveData<Response<List<CategoryResponse>>>()
    val categoryResponse: LiveData<Response<List<CategoryResponse>>> =
        categoryUseCase

    private val bannerUseCase = MutableLiveData<Response<List<BannerResponse>>>()
    val bannerResponse: LiveData<Response<List<BannerResponse>>> =
        bannerUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun fetchCategoryList() {
        viewModelScope.launch {
            categoryUseCase.value = Response.loading()
            try {
                categoryUseCase.value = Response.complete(
                    repository.fetchCategoryList()
                )
            } catch (error: Exception) {
                error.printStackTrace()
                categoryUseCase.value = Response.error(error)
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
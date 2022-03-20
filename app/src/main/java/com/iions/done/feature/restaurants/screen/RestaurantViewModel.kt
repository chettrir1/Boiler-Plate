package com.iions.done.feature.restaurants.screen

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.restaurants.data.RestaurantRepository
import com.iions.done.feature.restaurants.data.model.RestaurantRemoteBaseResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    var app: Application,
    private val repository: RestaurantRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val restaurantUseCase = MutableLiveData<Response<RestaurantRemoteBaseResponse>>()
    val restaurantResponse: LiveData<Response<RestaurantRemoteBaseResponse>> =
        restaurantUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun getRestaurants(filter: String?, page: Int) {
        viewModelScope.launch {
            restaurantUseCase.value = Response.loading()
            try {
                restaurantUseCase.value = Response.complete(
                    repository.getRestaurants(filter, page)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                restaurantUseCase.value = Response.error(error)
            }
        }
    }

}
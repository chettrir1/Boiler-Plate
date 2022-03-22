package com.iions.done.feature.restaurants.screen.detail

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.groceries.data.model.AddToCartResponse
import com.iions.done.feature.restaurants.data.RestaurantRepository
import com.iions.done.feature.restaurants.data.model.RestaurantDetailRemoteBaseResponse
import com.iions.done.feature.restaurants.data.model.RestaurantRemoteBaseResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(
    var app: Application,
    private val repository: RestaurantRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val restaurantUseCase = MutableLiveData<Response<RestaurantDetailRemoteBaseResponse>>()
    val restaurantResponse: LiveData<Response<RestaurantDetailRemoteBaseResponse>> =
        restaurantUseCase

    private val addToCartUseCase = MutableLiveData<Response<AddToCartResponse>>()
    val addToCartResponse: LiveData<Response<AddToCartResponse>> =
        addToCartUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun isUserLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }

    fun getRestaurant(restaurantId: Int) {
        viewModelScope.launch {
            restaurantUseCase.value = Response.loading()
            try {
                restaurantUseCase.value = Response.complete(
                    repository.getRestaurantDetail(restaurantId)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                restaurantUseCase.value = Response.error(error)
            }
        }
    }

    fun requestAddToCart(itemId: Int?, itemType: String?, quantity: Int?) {
        viewModelScope.launch {
            addToCartUseCase.value = Response.loading()
            try {
                addToCartUseCase.value = Response.complete(
                    repository.addToCart(itemId, itemType, quantity)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                addToCartUseCase.value = Response.error(error)
            }
        }
    }

}
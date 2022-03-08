package com.iions.done.feature.groceries.screen.detail

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.groceries.data.GroceryRepository
import com.iions.done.feature.groceries.data.model.AddToCartResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroceryDetailViewModel @Inject constructor(
    var app: Application,
    private val repository: GroceryRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val addToCartUseCase = MutableLiveData<Response<List<AddToCartResponse>>>()
    val addToCartResponse: LiveData<Response<List<AddToCartResponse>>> =
        addToCartUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun requestAddToCart(itemId: Int?, itemType: String?) {
        viewModelScope.launch {
            addToCartUseCase.value = Response.loading()
            try {
                addToCartUseCase.value = Response.complete(
                    repository.addToCart(repository.getUserId(), itemId, itemType)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                addToCartUseCase.value = Response.error(error)
            }
        }
    }

}
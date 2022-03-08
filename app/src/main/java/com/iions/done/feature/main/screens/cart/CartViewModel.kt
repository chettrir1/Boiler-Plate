package com.iions.done.feature.main.screens.cart

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.CartBaseResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    var app: Application,
    private val repository: MainRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val cartUseCase = MutableLiveData<Response<CartBaseResponse>>()
    val cartResponse: LiveData<Response<CartBaseResponse>> =
        cartUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    private fun getAuthorizationToken(): String {
        return repository.getAuthorizationToken()
    }

    fun fetchCartList() {
        viewModelScope.launch {
            cartUseCase.value = Response.loading()
            try {
                cartUseCase.value = Response.complete(
                    repository.fetchCartList(getAuthorizationToken())
                )
            } catch (error: Exception) {
                error.printStackTrace()
                cartUseCase.value = Response.error(error)
            }
        }
    }

}
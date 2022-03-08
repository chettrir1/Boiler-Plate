package com.iions.done.feature.groceries.screen

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iions.done.feature.groceries.data.GroceryRepository
import com.iions.done.feature.groceries.data.model.GroceryResponse
import com.rosia.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroceryViewModel @Inject constructor(
    var app: Application,
    private val repository: GroceryRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val groceryUseCase = MutableLiveData<Response<Flow<PagingData<GroceryResponse>>>>()
    val groceryResponse: LiveData<Response<Flow<PagingData<GroceryResponse>>>> =
        groceryUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun getGroceries(filter: String?, category: String?, brand: String?) {
        viewModelScope.launch {
            groceryUseCase.value = Response.loading()
            try {
                groceryUseCase.value = Response.complete(
                    repository.getGroceries(filter, category, brand)?.cachedIn(viewModelScope)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                groceryUseCase.value = Response.error(error)
            }
        }
    }

}
package com.iions.done.feature.search.screens

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.search.data.SearchRepository
import com.iions.done.feature.search.data.model.SearchBaseResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    var app: Application,
    private val repository: SearchRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val searchUseCase = MutableLiveData<Response<SearchBaseResponse>>()
    val searchResponse: LiveData<Response<SearchBaseResponse>> =
        searchUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun requestSearch(query: String? = "") {
        viewModelScope.launch {
            searchUseCase.value = Response.loading()
            try {
                searchUseCase.value = Response.complete(
                    repository.requestSearch(query)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                searchUseCase.value = Response.error(error)
            }
        }
    }

}
package com.iions.done.feature.main.screens

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.main.data.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var app: Application,
    private val repository: MainRepository
) : AndroidViewModel(app), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun isUserLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }

}
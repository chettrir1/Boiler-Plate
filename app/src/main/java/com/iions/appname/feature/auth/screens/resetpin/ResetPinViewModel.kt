package com.iions.appname.feature.auth.screens.resetpin

import android.app.Application
import androidx.lifecycle.*
import com.iions.appname.feature.auth.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class ResetPinViewModel @Inject constructor(
    var app: Application,
    private val repository: AuthRepository
) : AndroidViewModel(app), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}
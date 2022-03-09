package com.iions.done.feature.summary.screens

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.summary.data.SummaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class PaymentOptionViewModel @Inject constructor(
    var app: Application,
    private val repository: SummaryRepository
) : AndroidViewModel(app), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}
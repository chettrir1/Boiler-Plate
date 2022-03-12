package com.iions.done.feature.summary.screens.address

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.main.data.model.CartBaseResponse
import com.iions.done.feature.summary.data.SummaryRepository
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseAddressViewModel @Inject constructor(
    var app: Application,
    private val repository: SummaryRepository
) : AndroidViewModel(app), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}
package com.iions.done.feature.main.screens.profile.edit

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.ProfileBaseResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    var app: Application,
    private val repository: MainRepository
) : AndroidViewModel(app), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}
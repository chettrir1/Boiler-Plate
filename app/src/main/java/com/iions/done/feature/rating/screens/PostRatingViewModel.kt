package com.iions.done.feature.rating.screens

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.rating.data.RatingRepository
import com.iions.done.feature.rating.data.model.PostRatingResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostRatingViewModel @Inject constructor(
    var app: Application,
    private val repository: RatingRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val ratingUseCase = MutableLiveData<Response<PostRatingResponse>>()
    val ratingResponse: LiveData<Response<PostRatingResponse>> =
        ratingUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun isUserLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }

    fun postRating(itemId: Int, rating: Int, comment: String) {
        viewModelScope.launch {
            ratingUseCase.value = Response.loading()
            try {
                ratingUseCase.value = Response.complete(
                    repository.postRating(itemId, rating, comment)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                ratingUseCase.value = Response.error(error)
            }
        }
    }

}
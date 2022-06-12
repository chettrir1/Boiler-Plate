package com.iions.done.feature.rating.data.remote

import com.iions.done.exceptions.FailedResponseException
import com.iions.done.feature.rating.data.RatingRepository
import com.iions.done.feature.rating.data.model.PostRatingResponse
import com.iions.done.remote.ApiService
import javax.inject.Inject

class RatingRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : RatingRepository.Remote {

    override suspend fun postRating(
        authorizationToken: String,
        itemId: Int,
        rating: Int,
        comment: String
    ): PostRatingResponse? {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["item_id"] = itemId
        requestParams["star_rating"] = rating
        requestParams["comment"] = comment
        val remoteResponse = apiService.postRating(authorizationToken, requestParams)
        if (remoteResponse.status == true) {
            throw FailedResponseException(
                remoteResponse.status!!,
                remoteResponse.message.toString()
            )
        } else {
            return remoteResponse.response
        }
    }
}